package test.controller;

import com.revpasswordmanager.controller.UserController;
import com.revpasswordmanager.service.IPasswordManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private IPasswordManagerService service;

    private UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController(service);
    }

    @Test
    void testRegisterUser() {
        Scanner scanner = mock(Scanner.class);
        controller.registerUser(scanner);
        verify(service).registerUser(scanner);
    }

    @Test
    void testLoginUser() {
        Scanner scanner = mock(Scanner.class);
        when(service.loginUser(scanner)).thenReturn(true);
        controller.loginUser(scanner);
        verify(service).loginUser(scanner);
    }
}
