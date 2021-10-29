package com.example.MyBookShopApp.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestAOPController {

    private final UuidService uuidService;
    private final CustomUuidService customUuidService;

    @GetMapping("uuid")
    public ResponseEntity<?> handleUuid() {
        return ResponseEntity.ok().body(uuidService.generateUuid(Math.random()));
    }

    @GetMapping("uuid/custom")
    public ResponseEntity<?> handleCustomUuid() throws Exception {
        return ResponseEntity.ok().body(customUuidService.generateCustomUuid(Math.random()));
    }

}
