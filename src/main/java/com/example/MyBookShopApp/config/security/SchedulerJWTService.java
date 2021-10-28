package com.example.MyBookShopApp.config.security;

import com.example.MyBookShopApp.entity.token.JWTBlackListEntity;
import com.example.MyBookShopApp.service.JWTBlackListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerJWTService {

    private final JWTBlackListService jwtBlackListService;

    @Scheduled(cron = "00 00 * * 1 *")
    public void deleteOldTokens() {
        List<JWTBlackListEntity> jwtBlackList = jwtBlackListService.getOldTokens();
        if (!jwtBlackList.isEmpty()) {
            jwtBlackListService.deleteOldToken();
            log.info(String.format("Из BlackListToken было удалено: %d записей", jwtBlackList.size()));
        }
    }
}
