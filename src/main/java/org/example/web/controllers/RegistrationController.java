package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.RegistrationService;
import org.example.web.dto.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private Logger logger = Logger.getLogger(RegistrationController.class);
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String registration(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        logger.info("GET /registration returns registration_page.html");
        return "registration_page";
    }

    @PostMapping(value = "/create")
    public String registerNewUser(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        registrationService.sout();
        return "redirect:/login";
    }
}
