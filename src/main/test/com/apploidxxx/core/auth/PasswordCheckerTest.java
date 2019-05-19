package com.apploidxxx.core.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class PasswordCheckerTest {

    @Test
    void checkEquals() {
        String password = "password";

        assertTrue(PasswordChecker.checkEquals("password", password));
        assertFalse(PasswordChecker.checkEquals("passwor", password));
        assertFalse(PasswordChecker.checkEquals(String.valueOf(("password" + "everything is an object").hashCode()*31), password));
    }

    @Test
    void checkHash(){
        String password = "password";

        String hashedP = PasswordChecker.hashPassword(password);

        assertEquals(hashedP, PasswordChecker.hashPassword("password"));
        assertNotEquals(hashedP, PasswordChecker.hashPassword("ssword"));
    }
}