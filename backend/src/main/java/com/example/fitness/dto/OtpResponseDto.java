package com.example.fitness.dto;

public class OtpResponseDto {
	private String msg;
	public OtpResponseDto(String msg) {
        this.msg = msg;
    }
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
