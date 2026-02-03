package com.example.fitness.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.Service.*;
import com.example.fitness.dto.DashboardResponseDto;
import com.example.fitness.entity.User;
import com.example.fitness.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/dashboard")
public class DashboardController {
	private final DashboardService dashboardService;
	
    private final JwtUtil jwtUtil;
    private final UserService userservice ;
    public DashboardController(
    	UserService userservice,
        DashboardService dashboardService,
        JwtUtil jwtUtil
    ) {
        this.dashboardService = dashboardService;
        this.jwtUtil = jwtUtil;
		this.userservice = userservice;
    }

    @GetMapping
    public ResponseEntity<DashboardResponseDto>  dashboard(
        @RequestHeader("Authorization") String authHeader
    )
    {	
    	 if (authHeader == null || !authHeader.startsWith("Bearer ")) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
    	String token = authHeader.substring(7);
    	
    	 if (!jwtUtil.isTokenValid(token)) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }

        String email = jwtUtil.extractEmail(token);

        User user = userservice.getUserByEmail(email);
        DashboardResponseDto response =dashboardService.getDashboardData(user.getUserId());
        return ResponseEntity.ok(response);
    }
}
