package com.example.demo.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class emailValidation {

    private static final String EMAIL_REGEX = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public String getValidationMessage(String email, boolean isValid) {
        if (isValid) {
            return "Email address is valid";
        } else {
            if (email == null || email.isEmpty()) {
                return "Email address cannot be empty";
            }
            if (!email.contains("@")) {
                return "Email must contain @";
            }
            if (!email.contains(".")) {
                return "Email must contain a domain";
            }
            return "Email format is invalid";
        }
    }
}