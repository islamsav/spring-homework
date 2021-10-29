package com.example.MyBookShopApp.aop;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUuidService {

    public UuidServiceResponse generateCustomUuid(Double rnd) throws Exception {
        if (rnd >= 0.5) {
            UuidServiceResponse uuidServiceResponse = new UuidServiceResponse();
            uuidServiceResponse.setUuid("Custom_" + UUID.randomUUID());
            try {
                Thread.sleep((long) (rnd * 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return uuidServiceResponse;
        } else {
            throw new Exception("rnd is less than 0.5");
        }
    }

    public UuidServiceResponse generateCustomUuid() {
        UuidServiceResponse uuidServiceResponse = new UuidServiceResponse();
        uuidServiceResponse.setUuid("Custom_" + UUID.randomUUID());
        return uuidServiceResponse;
    }
}
