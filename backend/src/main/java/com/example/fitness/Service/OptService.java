package com.example.fitness.Service;

import com.example.fitness.dto.OtpRequestDto;
import com.example.fitness.dto.OtpResponseDto;

public interface OptService {
	OtpResponseDto generateOtp(OtpRequestDto dto);

    OtpResponseDto verifyOtp(OtpRequestDto dto);
}
