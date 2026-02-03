package com.example.fitness.ServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.fitness.Service.EmailService;
@Service
public class EmailServiceImple implements EmailService {
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(String to, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + text + "\n\nThis OTP is valid for login.");
		mailSender.send(message);
		System.out.println("Email sent to " + to);
	}

}
