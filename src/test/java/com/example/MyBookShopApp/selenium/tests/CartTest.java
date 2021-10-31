package com.example.MyBookShopApp.selenium.tests;

import com.codeborne.selenide.*;
import com.example.MyBookShopApp.selenium.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartTest {

    private MainPage mainPage;
    private SlugPage slugPage;

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:8085/");
        Configuration.pageLoadTimeout = 5000;
//        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        Configuration.browserVersion = "95.0";
        Configuration.startMaximized = true;
        Selenide.open("http://localhost:8085/");
        mainPage = new MainPage();
        slugPage = new SlugPage();
    }

    @Test
    public void goToBookPageAndAddBookToCard() {
        // кликаем по книге с текстом
        SelenideElement ticket = $x("//*[text()='Randy and the Mob']");
        ticket.should(Condition.visible).click(ClickOptions.usingJavaScript());

        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "http://localhost:8085/books/lNv3IiN");

        SelenideElement buyButton = slugPage.cartButton
                .should(Condition.visible);
        buyButton.click();
        Selenide.sleep(500);

        mainPage.goToCartPage();

        ticket.should(Condition.visible).click();

        $x("//*[@data-sendstatus='UNLINK']").click();

        $(Selectors.byText("Корзина пуста")).should(Condition.visible);

        ticket.should(Condition.not(Condition.visible));
    }
}
