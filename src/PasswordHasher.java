/**
 * Password Hasher — securely hashes and verifies passwords using bcrypt-style logic.
 *
 * This module is COMPLETE. Your task is in PasswordPolicyEngine.java.
 *
 * Author: Ravi Krishnan (Security team)
 * Last Modified: 2026-03-12
 */

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 10000;

    public String hash(String password) {
        byte[] salt = generateSalt();
        byte[] hash = pbkdf2(password, salt, ITERATIONS);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        String hashStr = Base64.getEncoder().encodeToString(hash);
        return saltStr + ":" + hashStr;
    }

    public boolean verify(String password, String storedHash) {
        String[] parts = storedHash.split(":");
        if (parts.length != 2) return false;
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
        byte[] actualHash = pbkdf2(password, salt, ITERATIONS);
        return MessageDigest.isEqual(expectedHash, actualHash);
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] pbkdf2(String password, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = password.getBytes("UTF-8");
            for (int i = 0; i < iterations; i++) {
                digest.update(salt);
                hash = digest.digest(hash);
            }
            return hash;
        } catch (Exception e) {
            throw new RuntimeException("Hash computation failed", e);
        }
    }
}
