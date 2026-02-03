package test.controller;

import com.revpasswordmanager.controller.SecurityQuestionController;
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
public class SecurityQuestionControllerTest {

    @Mock
    private IPasswordManagerService service;

    private SecurityQuestionController controller;

    @BeforeEach
    void setUp() {
        controller = new SecurityQuestionController(service);
    }

    @Test
    void testManageSecurityQuestions() {
        Scanner scanner = mock(Scanner.class);
        controller.manageSecurityQuestions(scanner);
        verify(service).manageSecurityQuestions(scanner);
    }
}
