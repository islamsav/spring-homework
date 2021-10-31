package com.example.MyBookShopApp.selenium.tests;

import com.codeborne.selenide.*;
import com.example.MyBookShopApp.selenium.pages.AuthPage;
import com.example.MyBookShopApp.selenium.pages.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationTest {

    private MainPage mainPage;
    private AuthPage authPage;

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:8085/");
        Configuration.pageLoadTimeout = 5000;
//        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        Configuration.browserVersion = "95.0";
        Configuration.startMaximized = true;
        mainPage = new MainPage();
        authPage = new AuthPage();
        Selenide.open("http://localhost:8085/");
    }

    @AfterEach
    void close() {
        Selenide.localStorage().clear();
        Selenide.sessionStorage().clear();
    }

    private final String login = "test@test.test";
    private final String pass = "123456";


    @Test
    public void authAndLogoutTest() {
        // авторизация
        mainPage
                .clickBySignButton()
                .selectEmailRadio()
                .fillTheEmailFieldAndNextClick(login)
                .fillTheCodeAndSignIn(pass);
        // авторизованная зона на странице /my
        $(Selectors.byText(login)).should(Condition.visible);
        $(Selectors.byText("Выход")).should(Condition.visible);
        $(Selectors.byText("На счете 0 рублей")).should(Condition.visible);
        SelenideElement exit = $(Selectors.byText("Выход"));
        exit.should(Condition.visible).click();
        exit.should(Condition.not(Condition.visible));
    }

    @Test
    public void fieldValidationTest() {
        mainPage
                .clickBySignButton()
                .selectEmailRadio()
                .clickNextButton()
                .checkErrorText("Это поле обязательно для заполнения")
                .fillTheEmailFieldAndNextClick(login)
                .fillTheCodeAndSignIn("")
                .clickSignInButton()
                .checkErrorText("Это поле обязательно для заполнения.");

        $(Selectors.byText("Зарегистрироваться")).should(Condition.visible);
        $(Selectors.byText("На Ваш e-mail отправлена ссылка для входа и код. Перейдите по ссылке или введите код ниже:"))
                .should(Condition.visible);
    }

    @Test
    void registrationNewUser() {
        String name = RandomStringUtils.random(5, "abcdefghijkqwz").toLowerCase(Locale.ROOT);
        String phone = RandomStringUtils.random(10, false, true).toLowerCase(Locale.ROOT);
        String email = RandomStringUtils.random(10, "abcdefghijkqwz").toLowerCase(Locale.ROOT) + "@test.ru";
        String pass = RandomStringUtils.random(6, false, true);

        // авторизация
        mainPage
                .clickBySignButton()
                .goToRegPage()
                .fillName(name)
                .fillAndSubmitPhone(phone)
                .fillAndSubmitEmail(email)
                .fillPass(pass)
                .registration();

        $(Selectors.byText("Введите e-mail или телефон")).should(Condition.visible);

        // проверяем что в базе есть новый юзер

        authPage
                .selectEmailRadio()
                .fillTheEmailFieldAndNextClick(email)
                .fillTheCodeAndSignIn(pass);
        $(Selectors.byText(email)).should(Condition.visible);
        $(Selectors.byText("Выход")).should(Condition.visible);

    }
}
