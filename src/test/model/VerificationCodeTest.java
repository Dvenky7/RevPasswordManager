package test.model;

import com.revpasswordmanager.model.VerificationCode;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationCodeTest {

    @Test
    void testVerificationCode() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        VerificationCode vc = new VerificationCode(1, "123456", "RECOVERY", now);

        assertEquals(1, vc.getUserId());
        assertEquals("123456", vc.getCode());
        assertEquals("RECOVERY", vc.getPurpose());
        assertEquals(now, vc.getExpiryTime());

        vc.setId(99);
        assertEquals(99, vc.getId());

        vc.setUsed(true);
        assertTrue(vc.isUsed());
    }
}
