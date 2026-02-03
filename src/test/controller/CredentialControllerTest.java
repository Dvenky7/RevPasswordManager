package test.controller;

import com.revpasswordmanager.controller.CredentialController;
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
public class CredentialControllerTest {

    @Mock
    private IPasswordManagerService service;

    private CredentialController controller;

    @BeforeEach
    void setUp() {
        controller = new CredentialController(service);
    }

    @Test
    void testAddCredential() {
        Scanner scanner = mock(Scanner.class);
        controller.addCredential(scanner);
        verify(service).addCredential(scanner);
    }

    @Test
    void testListCredentials() {
        controller.listCredentials();
        verify(service).listCredentials();
    }
}
