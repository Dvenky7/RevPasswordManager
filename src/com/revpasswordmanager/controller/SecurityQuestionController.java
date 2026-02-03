package com.revpasswordmanager.controller;

import com.revpasswordmanager.service.IPasswordManagerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class SecurityQuestionController {
    private static final Logger logger = LogManager.getLogger(SecurityQuestionController.class);
    private final IPasswordManagerService passwordManagerService;

    public SecurityQuestionController(IPasswordManagerService passwordManagerService) {
        this.passwordManagerService = passwordManagerService;
    }

    public void manageSecurityQuestions(Scanner scanner) {
        logger.info("Received request to manage security questions");
        passwordManagerService.manageSecurityQuestions(scanner);
    }
}
