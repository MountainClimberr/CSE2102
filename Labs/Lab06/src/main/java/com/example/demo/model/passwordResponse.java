package com.example.demo.model;

public class passwordResponse {
    private String password;
    private String quality;
    private int score;
    private String feedback;

    public passwordResponse() {}

    public passwordResponse(String password, String quality, int score, String feedback) {
        this.password = password;
        this.quality = quality;
        this.score = score;
        this.feedback = feedback;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}