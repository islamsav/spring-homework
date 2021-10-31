package com.example.MyBookShopApp.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {

    private final SelenideElement emailRadio = $x("//*[@class='toggle-text' and text()='E-mail']");
    private final SelenideElement emailInput = $(By.id("mail"));
    private final SelenideElement sendAuth = $(By.id("sendauth"));
    private final SelenideElement mailCode = $(By.id("mailcode"));
    private final SelenideElement toComeInMail = $(By.id("toComeInMail"));
    private final SelenideElement regLink = $x("//*[@class='Login-reg' and contains(text(),'Зарег')]");


    public RegPage goToRegPage() {
        this.regLink.should(Condition.visible).click();
        return Selenide.page(RegPage.class);
    }


    public AuthPage selectEmailRadio() {
        this.emailRadio
                .should(Condition.visible)
                .click();
        return Selenide.page(AuthPage.class);
    }

    public AuthPage clickSignInButton() {
        toComeInMail.should(Condition.visible).click();
        return this;
    }

    public AuthPage clickNextButton() {
        sendAuth.should(Condition.visible).click();
        return this;
    }

    public AuthPage fillTheEmailFieldAndNextClick(String email) {
        this.emailInput
                .should(Condition.visible)
                .should(Condition.enabled)
                .setValue(email);
        this.sendAuth.should(Condition.enabled).click();
        return Selenide.page(AuthPage.class);
    }

    public AuthPage fillTheCodeAndSignIn(String code) {
        this.mailCode
                .should(Condition.visible)
                .should(Condition.enabled)
                .setValue(code);
        this.toComeInMail.should(Condition.enabled).click();
        return this;
    }

    public AuthPage checkErrorText(String text) {
        SelenideElement error = $(Selectors.byClassName("form-error"));
        error.should(Condition.visible).should(Condition.textCaseSensitive(text));
        return this;
    }
}
