package com.example.schoolswap.Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {

    @Test
    void hashPassword_ShouldReturnValidSHA256Hash() {
        String password = "mysecretpassword";
        String hash = HashUtil.hashPassword(password);

        assertNotNull(hash);
        assertEquals(64, hash.length()); // SHA-256 is 256 bits, which is 64 hex characters
    }

    @Test
    void hashPassword_SamePassword_ShouldProduceSameHash() {
        String password = "password123";
        String hash1 = HashUtil.hashPassword(password);
        String hash2 = HashUtil.hashPassword(password);

        assertEquals(hash1, hash2);
    }

    @Test
    void hashPassword_DifferentPasswords_ShouldProduceDifferentHashes() {
        String hash1 = HashUtil.hashPassword("password123");
        String hash2 = HashUtil.hashPassword("Password123"); // Capital P

        assertNotEquals(hash1, hash2);
    }
}