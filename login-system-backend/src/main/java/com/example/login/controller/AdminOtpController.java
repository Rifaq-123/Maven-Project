package com.example.login.controller;

import org.springframework.web.bind.annotation.*;

import com.example.login.entity.AdminOtp;
import com.example.login.service.AdminOtpService;

@RestController
@RequestMapping("/api/admin/otp")
public class AdminOtpController {

    private final AdminOtpService service;

    public AdminOtpController(AdminOtpService service) {
        this.service = service;
    }

    /**
     * Generate OTP for student/teacher
     *
     * Example:
     * POST /api/admin/otp/generate?role=STUDENT&purpose=REGISTER
     */
    @PostMapping("/generate")
    public AdminOtp generateOtp(
            @RequestParam String role,
            @RequestParam String purpose
    ) {
        return service.generateOtp(role, purpose);
    }
}
