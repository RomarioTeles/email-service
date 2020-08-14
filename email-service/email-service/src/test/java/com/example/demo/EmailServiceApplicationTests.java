package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceApplicationTests {

	@Autowired
	private EmailService emailService;
	
	@Test
	void sendSimpleEmail() {
		
		boolean isSend = emailService.sendSimpleMessage(null, 
				new String[]{"romario.teles@ymail.com"}, 
				"Test e-mail", 
				"Teste e-mail...");
		
		assertTrue(isSend);
		
	}
	
	@Test
	void sendMessageWithAttachment() throws IOException {
		
		File file = File.createTempFile("attachment", ".txt");
		file.createNewFile();
		
		boolean isSend = emailService.sendMessageWithAttachment(null, 
				new String[]{"romario.teles@ymail.com"}, 
				"Test e-mail", 
				"Teste e-mail...",
				file);
		
		assertTrue(isSend);
		
	}

}
