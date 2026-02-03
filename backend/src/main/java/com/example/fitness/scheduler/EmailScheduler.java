//package com.example.fitness.scheduler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.example.fitness.Service.EmailService;
//
//@Component
//public class EmailScheduler {

//	@Autowired
//	private EmailService emailService; // Directly inject the class
//	
//	//@Scheduled(cron = "0/10 * * * * ?") 
//	public void sendScheduledEmail() {
//
//		// List of recipients
//		String[] recipients = {""};
//		String otp=otp();
//
//		//emailService.sendEmail(recipients, "Scheduled Email from Spring Boot",otp);
//		
//	}
//	private String otp() {
//		int otp=100000 +  new java.util.Random().nextInt(999999);
//		return String.valueOf(otp);
//	}
//}
//
