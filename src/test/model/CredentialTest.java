package test.model;

import com.revpasswordmanager.model.Credential;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CredentialTest {

    @Test
    void testCredentialGettersAndSetters() {
        Credential cred = new Credential(1, "Google", "user@gmail.com", "encPass", "google.com", "My notes");
        cred.setId(10);

        assertEquals(10, cred.getId());
        assertEquals(1, cred.getUserId());
        assertEquals("Google", cred.getAccountName());
        assertEquals("user@gmail.com", cred.getUsername());
        assertEquals("encPass", cred.getEncryptedPassword());
        assertEquals("google.com", cred.getUrl());
        assertEquals("My notes", cred.getNotes());

        cred.setEncryptedPassword("newEnc");
        assertEquals("newEnc", cred.getEncryptedPassword());
    }
}
