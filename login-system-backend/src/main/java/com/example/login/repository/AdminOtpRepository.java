package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.AdminOtp;

import java.util.Optional;

public interface AdminOtpRepository extends JpaRepository<AdminOtp, Long> {

    Optional<AdminOtp> findByCodeAndPurposeAndUsedFalse(
        String code,
        String purpose
    );
}

