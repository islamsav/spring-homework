package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.config.security.BookstoreUserDetail;
import com.example.MyBookShopApp.config.security.jwt.JWTUtil;
import com.example.MyBookShopApp.dto.ContactConfirmationPayload;
import com.example.MyBookShopApp.dto.ContactConfirmationResponse;
import com.example.MyBookShopApp.dto.RegistrationForm;
import com.example.MyBookShopApp.dto.UserDto;
import com.example.MyBookShopApp.entity.user.UserEntity;
import com.example.MyBookShopApp.repository.BookstoreUserRepository;
import com.example.MyBookShopApp.repository.JwtBlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookstoreUserRegisterService {

    private final BookstoreUserRepository bookstoreUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BookstoreUserDetailService bookstoreUserDetailService;
    private final JWTUtil jwtUtil;
    private final JwtBlackListRepository jwtBlackListRepository;

    public UserEntity registerNewUser(RegistrationForm registrationForm) {
        UserEntity user = new UserEntity();
        if (bookstoreUserRepository.findUserEntitiesByEmail(registrationForm.getEmail()) == null) {
            user = new UserEntity();
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
        return user;
    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(), payload.getCode()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    public ContactConfirmationResponse jwtLogin(ContactConfirmationPayload payload) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(), payload.getCode()));
        BookstoreUserDetail userDetail = (BookstoreUserDetail) bookstoreUserDetailService.loadUserByUsername(payload.getContact());
        String jwtToken = jwtUtil.generateToken(userDetail);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);
        return response;
    }

    public Object getCurrentUser() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetail instanceof DefaultOAuth2User) {
            Map<String, Object> attr = ((DefaultOAuth2User) userDetail).getAttributes();
            String login = attr.get("login").toString();
            String email = attr.get("email").toString();
            return UserDto.builder()
                    .name(login)
                    .balance(0)
                    .email(email)
                    .phone(null)
                    .build();
        } else {
            return ((BookstoreUserDetail) userDetail).getUser();
        }
    }
}
