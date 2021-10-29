package com.example.MyBookShopApp.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UuidService {

    public UuidServiceResponse generateUuid(Double rnd) {
        UuidServiceResponse uuidServiceResponse = new UuidServiceResponse();
        uuidServiceResponse.setUuid(UUID.randomUUID().toString());
        try {
            Thread.sleep((long) (rnd * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uuidServiceResponse;
    }

    public UuidServiceResponse generateUuid() {
        UuidServiceResponse uuidServiceResponse = new UuidServiceResponse();
        uuidServiceResponse.setUuid(UUID.randomUUID().toString());
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uuidServiceResponse;
    }
}
