package com.generator.pingenerator.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneRegistryTest {

    private PhoneRegistry phoneTest;

    @BeforeEach
    void setUp(){
        phoneTest = new PhoneRegistry();
        phoneTest.setPhoneNumber("+34123456789");
    }

    @Test
    void testGetAttemps() {
        assertTrue(phoneTest.getAttemps() == 3);
    }

    @Test
    void testGetPhoneNumber() {
        assertTrue(phoneTest.getPhoneNumber().equals("+34123456789"));
    }

    @Test
    void testGetPinCode() {
        assertTrue(!phoneTest.getPinCode().equals(""));
    }

    @Test
    void testReduceAttemps() {
        phoneTest.reduceAttemps();
        assertTrue(phoneTest.getAttemps() == 2);
    }

    @Test
    void testSetPhoneNumber() {
        phoneTest.setPhoneNumber("+34987654321");
        assertTrue(phoneTest.getPhoneNumber().equals("+34987654321"));
    }

    @Test
    void testSetPinCode() {
        phoneTest.setPinCode("4321");
        assertTrue(phoneTest.getPinCode().equals("4321"));
    }

    @Test
    void testSetInvalidPhoneNumber() {
        phoneTest.setPhoneNumber("34987654321");
        assertTrue(!phoneTest.getPhoneNumber().equals("34987654321"));
    }

    @Test
    void testSetInvalidPinCode() {
        phoneTest.setPinCode("A321");
        assertTrue(!phoneTest.getPinCode().equals("A321"));
    }
}
