package com.example.demo;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public boolean sendSimpleMessage(String from, String[] to,String[] cc, String[] cco, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			if (from != null && !from.isEmpty()) {
				message.setFrom(from);
			}
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			
			if (cc != null && cc.length > 0) {
				message.setCc(cc);
			}
			if (cco != null && cco.length > 0) {
				message.setBcc(cco);
			}
			
			emailSender.send(message);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendMessageWithAttachment(String from, String[] to, String[] cc, String[] cco, String subject,
			String text, File attachment) {

		try {
			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			if (from != null && !from.isEmpty()) {
				helper.setFrom(from);
			}
			if (cc != null && cc.length > 0) {
				helper.setCc(cc);
			}
			if (cco != null && cco.length > 0) {
				helper.setBcc(cco);
			}

			if (attachment != null) {
				FileSystemResource file = new FileSystemResource(attachment);
				helper.addAttachment(file.getFilename(), file);
			}

			emailSender.send(message);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendSimpleMessage(String from, String[] to, String subject, String text) {
		return sendSimpleMessage(from, to, null, null, subject, text);
	}

	public boolean sendMessageWithAttachment(String from, String[] to, String subject, String text, File file) {
		return sendMessageWithAttachment(from, to, null, null, subject, text, file);
	}

}
