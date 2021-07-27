package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);
    private RegistrationService registrationService;

    @Autowired
    public LoginService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with user-data " + loginForm.toString());
        boolean newUserExist = registrationService.getAllUsers().stream()
                .anyMatch(e -> e.getUsername().equals(loginForm.getUsername()) && e.getPassword().equals(loginForm.getPassword()));
        return newUserExist || loginForm.getUsername().equals("root") && loginForm.getPassword().equals("123");
    }
}
