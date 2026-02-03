package com.example.fitness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByEmail(String email);

    Optional<Otp> findByEmailAndOtp(String email, String otp);
    void deleteById(long id);

    void deleteByEmail(String email);
}
