package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.demo.service.passwordQuality;

public class passwordQualityTest {

    private final passwordQuality service = new passwordQuality();

    @Test
    public void testWeakPassword() {
        int score = service.calculatePasswordScore("abc");
        assertEquals("Weak", service.getQualityRating(score));
        assertTrue(score < 30);
    }

    @Test
    public void testStrongPassword() {
        int score = service.calculatePasswordScore("P@ssw0rd05112005Test");
        assertEquals("Strong", service.getQualityRating(score));
        assertTrue(score >= 80);
    }

    @Test
    public void testEmptyPassword() {
        int score = service.calculatePasswordScore("");
        assertEquals(0, score);
    }

    @Test
    public void testFairPassword() {
        int score = service.calculatePasswordScore("password123");
        assertTrue(score >= 30 && score < 80);
    }

    @Test
    public void testLongPassword() {
        int score = service.calculatePasswordScore("ThisIsAVeryLongPassword123!");
        assertTrue(score >= 80);
    }
}