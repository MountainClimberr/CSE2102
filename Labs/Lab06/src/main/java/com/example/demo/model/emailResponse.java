package com.example.demo.model;

public class emailResponse {
    private String email;
    private boolean isValid;
    private String message;

    public emailResponse() {}

    public emailResponse(String email, boolean isValid, String message) {
        this.email = email;
        this.isValid = isValid;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}