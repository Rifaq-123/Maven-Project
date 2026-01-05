package com.example.login.service;

import org.springframework.stereotype.Service;

import com.example.login.entity.AdminOtp;
import com.example.login.repository.AdminOtpRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AdminOtpService {

    private final AdminOtpRepository repository;

    public AdminOtpService(AdminOtpRepository repository) {
        this.repository = repository;
    }

    public AdminOtp generateOtp(String role, String purpose) {

        // generate 6-digit OTP
        String otpCode = String.valueOf(100000 + new Random().nextInt(900000));

        AdminOtp otp = new AdminOtp();
        otp.setCode(otpCode);
        otp.setRole(role.toUpperCase());        // STUDENT / TEACHER
        otp.setPurpose(purpose.toUpperCase());  // REGISTER / RESET
        otp.setUsed(false);
        otp.setExpiresAt(LocalDateTime.now().plusHours(24));

        return repository.save(otp);
    }
}
