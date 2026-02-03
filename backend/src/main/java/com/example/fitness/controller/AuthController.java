package com.example.fitness.controller;

import com.example.fitness.Service.OptService;
import com.example.fitness.dto.AuthResponseDto;
import com.example.fitness.dto.LoginRequestDto;
import com.example.fitness.dto.OtpRequestDto;
import com.example.fitness.dto.OtpResponseDto;
import com.example.fitness.dto.RegisterRequestDto;
import com.example.fitness.entity.Role;
import com.example.fitness.entity.User;
import com.example.fitness.repository.RoleRepository;
import com.example.fitness.repository.UserRepository;
import com.example.fitness.security.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final OptService otp;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,OptService otp) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.otp=otp;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setHeightCm(dto.getHeightCm());
        user.setWeightKg(dto.getWeightKg());

        Role roleUser = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        user.setRoles(Set.of(roleUser));
        userRepository.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto dto) {

    
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        String token = jwtUtil.generateToken(dto.getEmail());

        return new AuthResponseDto(token);
    }
    @PostMapping("/otp")
    public OtpResponseDto otp(@RequestBody OtpRequestDto dto) {
        return otp.generateOtp(dto);
    }

    @PostMapping("/otp/verify")
    public OtpResponseDto verifyOtp(@RequestBody OtpRequestDto dto) {
        return otp.verifyOtp(dto);
    }

}
