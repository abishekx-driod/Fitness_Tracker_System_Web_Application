package com.example.fitness.ServiceImple;

import java.time.LocalDateTime;

import java.util.Random;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.fitness.Service.EmailService;
import com.example.fitness.Service.OptService;
import com.example.fitness.dto.OtpRequestDto;
import com.example.fitness.dto.OtpResponseDto;
import com.example.fitness.entity.Otp;
import com.example.fitness.repository.OtpRepository;

@Service
public class OtpServiceImple implements OptService {

	private final OtpRepository otpRepository;

	private final EmailService emailService;

	public OtpServiceImple(OtpRepository otpRepository, EmailService emailService) {
		this.otpRepository = otpRepository;
		this.emailService = emailService;
	}

	@Override
	public OtpResponseDto generateOtp(OtpRequestDto dto) {


//        // 6 digit OTP
//        int oyy=100000 + new Random().nextInt(900000);
//       // String otpValue = String.valueOf(100000 + new Random().nextInt(900000));
//        StringBuilder otpValue = new StringBuilder("");
//        while(oyy>0) {
//        	int r=oyy%10;
//        	oyy/=10;
//        	char ch=(char) (97+r);
//        	otpValue.append(String.valueOf( (ch)));
//        }
//        String oo=new String(otpValue);

		String otpValue = String.valueOf(100000 + new Random().nextInt(900000));
		LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
		Otp otp = new Otp();
		otp.setEmail(dto.getEmail());
		otp.setOtp(otpValue);
		otp.setExpiryTime(expiryTime);
		otpRepository.save(otp);
		emailService.sendEmail(dto.getEmail(), otpValue);
		// System.out.println("OTP for " + dto.getEmail() + " : " + otpValue);

		return new OtpResponseDto("OTP sent successfully");
	}

	// OTP VERIFY
	@Override
	public OtpResponseDto verifyOtp(OtpRequestDto dto) {
//optional
		// returns otpOPt
		// check is present
		// delete and print
		
		// else
		return otpRepository.findByEmailAndOtp(dto.getEmail(), dto.getOtp())
		        .map(otp -> {
		        	if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
		                otpRepository.deleteByEmail(otp.getEmail());
		                return new OtpResponseDto("OTP expired");
		            }
		            otpRepository.deleteByEmail(otp.getEmail());
		            return new OtpResponseDto("OTP verified successfully");
		        })
		        .orElse(new OtpResponseDto("Invalid OTP"));
//		return new OtpResponseDto("OTP verified successfully");

	}
}
