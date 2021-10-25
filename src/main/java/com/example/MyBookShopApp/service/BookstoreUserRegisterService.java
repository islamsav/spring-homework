package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.config.security.BookstoreUserDetail;
import com.example.MyBookShopApp.dto.ContactConfirmationPayload;
import com.example.MyBookShopApp.dto.ContactConfirmationResponse;
import com.example.MyBookShopApp.dto.RegistrationForm;
import com.example.MyBookShopApp.entity.user.UserEntity;
import com.example.MyBookShopApp.repository.BookstoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class BookstoreUserRegisterService {

    private final BookstoreUserRepository bookstoreUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void registerNewUser(RegistrationForm registrationForm) {
        if (bookstoreUserRepository.findUserEntitiesByEmail(registrationForm.getEmail()) == null) {
            UserEntity user = new UserEntity();
            user.setName(registrationForm.getName());
            user.setEmail(registrationForm.getEmail());
            user.setPassword(passwordEncoder.encode(registrationForm.getPass()));
            user.setPhone(registrationForm.getPhone());
            user.setBalance(0);
            user.setRegTime(LocalDateTime.now());
            user.setHash(String.valueOf(registrationForm.hashCode()));
            user.setReviews(new HashSet<>());
            bookstoreUserRepository.save(user);
        }
    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(), payload.getCode()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(true);
        return response;
    }

    public UserEntity getCurrentUser() {
        BookstoreUserDetail userDetail = (BookstoreUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetail.getUser();
    }
}
