package com.example.MyBookShopApp.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final ElementsCollection topBar = $$x("//*[@class='menu menu_main']//a[contains(@class,'menu-link')]");

    private final SelenideElement myButton = $x("(//*[@class='CartBlock']/*)[3]");
    private final SelenideElement cart = $x("(//*[@class='CartBlock']/*)[2]");


    public AuthPage clickBySignButton() {
        this.myButton.should(Condition.visible).click();
        return Selenide.page(AuthPage.class);
    }

    public void goToCartPage() {
        this.cart.should(Condition.visible).click();
    }
}
