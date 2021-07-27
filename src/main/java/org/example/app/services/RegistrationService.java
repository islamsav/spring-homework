package org.example.app.services;

import org.example.web.dto.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(RegistrationForm user) {
        userRepository.store(user);
    }

    public List<RegistrationForm> getAllUsers() {
        return userRepository.retreiveAll();
    }
}
