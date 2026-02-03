package test.model;

import com.revpasswordmanager.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void testUserConstructorsAndGetters() {
        User user = new User("username", "hash", "Name", "email@example.com");
        assertEquals("username", user.getUsername());
        assertEquals("hash", user.getMasterPasswordHash());
        assertEquals("Name", user.getName());
        assertEquals("email@example.com", user.getEmail());

        user.setId(1);
        assertEquals(1, user.getId());

        User userWithId = new User(2, "user2", "hash2", "Name2", "email2@example.com");
        assertEquals(2, userWithId.getId());
        assertEquals("user2", userWithId.getUsername());
    }

    @Test
    void testSetters() {
        User user = new User("u", "h", "n", "e");
        user.setUsername("newuser");
        user.setMasterPasswordHash("newhash");
        user.setName("New Name");
        user.setEmail("new@example.com");

        assertEquals("newuser", user.getUsername());
        assertEquals("newhash", user.getMasterPasswordHash());
        assertEquals("New Name", user.getName());
        assertEquals("new@example.com", user.getEmail());
    }
}
