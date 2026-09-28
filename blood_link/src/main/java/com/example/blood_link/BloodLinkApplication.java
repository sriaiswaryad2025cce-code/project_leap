package com.example.blood_link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BloodLink - Blood Donor Registration and Management System
 * Main entry point for the Spring Boot application.
 */
@SpringBootApplication
public class BloodLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodLinkApplication.class, args);
        System.out.println("✅ BloodLink Application Started Successfully!");
        System.out.println("🩸 API is running at: http://localhost:8080");
    }
}
