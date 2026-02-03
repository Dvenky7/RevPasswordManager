package test.util;

import com.revpasswordmanager.util.CryptoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CryptoUtilTest {

    @Test
    void testHashAndVerify() {
        String password = "password123";
        String hash = CryptoUtil.hashPassword(password);
        assertNotNull(hash);
        assertNotEquals(password, hash);

        assertTrue(CryptoUtil.verifyPassword(password, hash));
        assertFalse(CryptoUtil.verifyPassword("wrong", hash));
    }

    @Test
    void testEncryptDecrypt() {
        String plain = "SecretData";
        String password = "masterPassword";
        byte[] key = CryptoUtil.deriveKey(password);

        String encrypted = CryptoUtil.encrypt(plain, key);
        assertNotNull(encrypted);
        assertNotEquals(plain, encrypted);

        String decrypted = CryptoUtil.decrypt(encrypted, key);
        assertEquals(plain, decrypted);
    }
}
