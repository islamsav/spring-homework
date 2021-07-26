package org.example.app.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private Logger logger = Logger.getLogger(RegistrationService.class);

    public void sout() {
        logger.info("sout");
        System.out.println();
    }
}
