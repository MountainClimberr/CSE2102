package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class passwordQuality {

    public int calculatePasswordScore(String password) {
        if (password == null || password.isEmpty()) {
            return 0;
        }

        int score = 0;

        // Length check
        if (password.length() >= 8) score += 25;
        if (password.length() >= 12) score += 25;

        // Has lowercase
        if (password.matches(".*[a-z].*")) score += 10;

        // Has uppercase
        if (password.matches(".*[A-Z].*")) score += 10;

        // Has digit
        if (password.matches(".*\\d.*")) score += 15;

        // Has special character
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) score += 15;

        return Math.min(score, 100);
    }

    public String getQualityRating(int score) {
        if (score < 30) return "Weak";
        if (score < 60) return "Fair";
        if (score < 80) return "Good";
        return "Strong";
    }

    public String getFeedback(String password, int score) {
        if (score >= 80) {
            return "Excellent password!";
        }

        StringBuilder feedback = new StringBuilder("Suggestions: ");
        
        if (password.length() < 8) {
            feedback.append("Use at least 8 characters. ");
        }
        if (!password.matches(".*[a-z].*")) {
            feedback.append("Add lowercase letters. ");
        }
        if (!password.matches(".*[A-Z].*")) {
            feedback.append("Add uppercase letters. ");
        }
        if (!password.matches(".*\\d.*")) {
            feedback.append("Add numbers. ");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            feedback.append("Add special characters. ");
        }

        return feedback.toString();
    }
}