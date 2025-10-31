package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.emailValidation;
import com.example.demo.service.passwordQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class controller {

    @Autowired
    private passwordQuality passwordQuality;

    @Autowired
    private emailValidation emailValidation;

    @GetMapping("/")
    public String home() {
        return "Validation API is running!";
    }

    @PostMapping("/password-quality")
    public passwordResponse checkPasswordQuality(@RequestBody passwordRequest request) {
        String password = request.getPassword();
        int score = passwordQuality.calculatePasswordScore(password);
        String quality = passwordQuality.getQualityRating(score);
        String feedback = passwordQuality.getFeedback(password, score);

        return new passwordResponse(password, quality, score, feedback);
    }

    @PostMapping("/email-address-valid")
    public emailResponse validateEmail(@RequestBody emailRequest request) {
        String email = request.getEmail();
        boolean isValid = emailValidation.isValidEmail(email);
        String message = emailValidation.getValidationMessage(email, isValid);

        return new emailResponse(email, isValid, message);
    }
}