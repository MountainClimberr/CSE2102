package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.demo.service.emailValidation;

public class emailValidationTest {

    private final emailValidation service = new emailValidation();

    @Test
    public void testValidEmail() {
        assertTrue(service.isValidEmail("test@email.com"));
    }

    @Test
    public void testValidEmailWithNumbers() {
        assertTrue(service.isValidEmail("test123@email.com"));
    }

    @Test
    public void testInvalidEmailNoAt() {
        assertFalse(service.isValidEmail("testemail.com"));
    }

    @Test
    public void testInvalidEmailNoDomain() {
        assertFalse(service.isValidEmail("test@"));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(service.isValidEmail(""));
    }
}