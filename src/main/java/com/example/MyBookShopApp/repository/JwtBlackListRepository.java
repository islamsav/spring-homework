package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.token.JWTBlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtBlackListRepository extends JpaRepository<JWTBlackListEntity, Integer> {

    @Query(value = "DELETE FROM token WHERE created_at < date_trunc('week', now()) - INTERVAL '7days' ",
            nativeQuery = true)
    void deleteOldTokens();

    @Query(value = "SELECT * FROM token WHERE created_at < date_trunc('week', now()) - INTERVAL '7days' ",
            nativeQuery = true)
    List<JWTBlackListEntity> getOldTokens();

    JWTBlackListEntity findJwtBlacklistByToken(String token);
}
