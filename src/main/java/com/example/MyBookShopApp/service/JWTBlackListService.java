package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.token.JWTBlackListEntity;
import com.example.MyBookShopApp.repository.JwtBlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JWTBlackListService {

    private final JwtBlackListRepository jwtBlackListRepository;


    public JWTBlackListEntity getByToken(String token) {
        return this.jwtBlackListRepository.findJwtBlacklistByToken(token);
    }

    public JWTBlackListEntity saveToken(JWTBlackListEntity jwtBlacklist) {
        return this.jwtBlackListRepository.save(jwtBlacklist);
    }

    public void deleteOldToken() {
        this.jwtBlackListRepository.deleteOldTokens();
    }

    public List<JWTBlackListEntity> getOldTokens() {
        return this.jwtBlackListRepository.getOldTokens();
    }
}
