package com.example.MyBookShopApp.selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest
public class MainPageSeleniumTest {

    @BeforeAll
    static void setup() {
        Configuration.pageLoadTimeout = 5000;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        Configuration.browserVersion = "95.0";
    }

    @Test
    public void testMainPageAccess() {
        open("http://localhost:8085/");
        Selenide.sleep(2500);
    }
}
