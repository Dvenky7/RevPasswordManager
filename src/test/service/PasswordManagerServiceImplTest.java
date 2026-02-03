package test.service;

import com.revpasswordmanager.dao.ICredentialDao;
import com.revpasswordmanager.dao.IUserDao;
import com.revpasswordmanager.model.User;
import com.revpasswordmanager.service.IOTPService;
import com.revpasswordmanager.service.PasswordManagerServiceImpl;
import com.revpasswordmanager.util.CryptoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PasswordManagerServiceImplTest {

    @Mock
    private IUserDao userDao;

    @Mock
    private ICredentialDao credentialDao;

    @Mock
    private IOTPService otpService;

    @InjectMocks
    private PasswordManagerServiceImpl passwordManagerService;

    @BeforeEach
    void setUp() throws Exception {
        setPrivateField(passwordManagerService, "userDao", userDao);
        setPrivateField(passwordManagerService, "credentialDao", credentialDao);
        setPrivateField(passwordManagerService, "otpService", otpService);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void testRegisterUser() {
        String input = "testuser\npassword123\nTest Name\ntest@example.com\nquestion1\nanswer1\nquestion2\nanswer2\nquestion3\nanswer3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        when(userDao.getUserByUsername("testuser")).thenReturn(new User(1, "testuser", "hash", "name", "email"));

        passwordManagerService.registerUser(scanner);

        verify(userDao, times(1)).createUser(any(User.class));
    }

    @Test
    void testLoginUser_Success() {
        String password = "password123";
        String hashedPassword = CryptoUtil.hashPassword(password);
        User user = new User("testuser", hashedPassword, "Test Name", "test@example.com");

        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        String input = "testuser\npassword123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        boolean result = passwordManagerService.loginUser(scanner);

        assertTrue(result);
    }

    @Test
    void testLoginUser_Failure() {
        String password = "password123";
        String hashedPassword = CryptoUtil.hashPassword(password);
        User user = new User("testuser", hashedPassword, "Test Name", "test@example.com");

        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        String input = "testuser\nwrongpassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        boolean result = passwordManagerService.loginUser(scanner);

        assertFalse(result);
    }

    @Test
    void testLoginUser_EmptyPassword() {
        String input = "testuser\n\n"; // Empty password (newline immediately)
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        boolean result = passwordManagerService.loginUser(scanner);

        assertFalse(result, "Login should fail when password is empty");
        // Verify that we didn't crash
    }

    @Test
    void testRegisterUser_EmptyPassword() {
        String input = "testuser\n\n"; // Empty password
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        passwordManagerService.registerUser(scanner);

        // Verify that createUser was NEVER called
        verify(userDao, never()).createUser(any(User.class));
    }
}
