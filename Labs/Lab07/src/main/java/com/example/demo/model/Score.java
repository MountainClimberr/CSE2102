package com.example.demo.model;

public class Score {
    private int correct = 0;
    private int total = 0;
    
    public int getCorrect() {
        return correct;
    }
    
    public void setCorrect(int correct) {
        this.correct = correct;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public void incrementCorrect() {
        correct++;
    }
    
    public void incrementTotal() {
        total++;
    }
    
    public double getPercentage() {
        if (total == 0) return 0.0;
        return (correct * 100.0) / total;
    }
}