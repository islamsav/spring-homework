package com.example.MyBookShopApp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MyBookShopAppApplicationTests {

    @Value("${auth.secret}")
    private String secret;

    private final MyBookShopAppApplication myBookShopAppApplication;

    @Autowired
    public MyBookShopAppApplicationTests(MyBookShopAppApplication myBookShopAppApplication) {
        this.myBookShopAppApplication = myBookShopAppApplication;
    }

    @Test
    void contextLoads() {
        assertNotNull(myBookShopAppApplication);
    }

    @Test
    public void verifySecret() {
        assertThat(secret, Matchers.containsString("box"));
    }
}
