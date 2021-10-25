package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.config.security.BookstoreUserDetail;
import com.example.MyBookShopApp.entity.user.UserEntity;
import com.example.MyBookShopApp.repository.BookstoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookstoreUserDetailService implements UserDetailsService {

    private final BookstoreUserRepository bookstoreUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = bookstoreUserRepository.findUserEntitiesByEmail(s);
        if (user != null) {
            return new BookstoreUserDetail(user);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
