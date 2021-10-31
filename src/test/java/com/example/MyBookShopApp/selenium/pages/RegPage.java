package com.example.MyBookShopApp.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RegPage {

    private final SelenideElement nameField = $(By.id("name"));
    private final SelenideElement phoneField = $(By.id("phone"));
    private final SelenideElement mailField = $(By.id("mail"));
    private final SelenideElement passField = $(By.id("pass"));

    private final SelenideElement submitPhone = $(By.id("submitPhone"));
    private final SelenideElement submitMail = $(By.id("submitMail"));
    private final SelenideElement phoneCode = $(By.id("phoneCode"));
    private final SelenideElement mailCode = $(By.id("mailCode"));

    private final SelenideElement registrationButton = $(By.id("registration"));

    public RegPage fillAndSubmitEmail(String email) {
        mailField
                .should(Condition.attribute("placeholder", "Введите e-mail"))
                .should(Condition.empty)
                .setValue(email);
        nameField.click();
        submitMail.should(Condition.visible).should(Condition.enabled).click();
        $(Selectors.byText("Вам направлен код подтверждения в письме на почту")).should(Condition.visible);
        mailCode.should(Condition.visible).should(Condition.enabled).setValue("111-111");
        nameField.click();
        return this;
    }

    public RegPage fillName(String name) {
        nameField
                .should(Condition.attribute("placeholder", "Введите Имя"))
                .should(Condition.empty)
                .setValue(name);
        return this;
    }

    public RegPage fillAndSubmitPhone(String phone) {
        phoneField
                .should(Condition.attribute("placeholder", "Введите телефон"))
                .should(Condition.empty)
                .setValue(phone);
        nameField.click();

        submitPhone.should(Condition.visible).should(Condition.enabled).click();
        $(Selectors.byText("Вам направлен код подтверждения по SMS")).should(Condition.visible);

        phoneCode.should(Condition.visible).should(Condition.enabled).setValue("111-111");
        nameField.click();
        $(Selectors.byText("Код успешно подтвержден")).should(Condition.visible);
        return this;
    }

    public RegPage fillPass(String pass) {
        passField
                .should(Condition.attribute("placeholder", "Введите пароль"))
                .should(Condition.empty)
                .setValue(pass);
        nameField.click();
        return this;
    }

    public void registration() {
        registrationButton.should(Condition.enabled).click();
    }
}
