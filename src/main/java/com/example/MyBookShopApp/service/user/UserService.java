package com.example.MyBookShopApp.service.user;

import com.example.MyBookShopApp.entity.user.UserEntity;
import com.example.MyBookShopApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void save(UserEntity user) {
        userRepository.save(user);
    }
}
