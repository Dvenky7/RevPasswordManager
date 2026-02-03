package test.controller;

import com.revpasswordmanager.controller.VerificationCodeController;
import com.revpasswordmanager.service.IPasswordManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VerificationCodeControllerTest {

    @Mock
    private IPasswordManagerService service;

    private VerificationCodeController controller;

    @BeforeEach
    void setUp() {
        controller = new VerificationCodeController(service);
    }

    @Test
    void testRecoverPassword() {
        Scanner scanner = mock(Scanner.class);
        controller.recoverPassword(scanner);
        verify(service).recoverPassword(scanner);
    }
}
