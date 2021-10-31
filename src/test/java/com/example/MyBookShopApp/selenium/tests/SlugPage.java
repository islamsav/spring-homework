package com.example.MyBookShopApp.selenium.tests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SlugPage {

    public SelenideElement buyButton = $x("//*[@data-sendstatus='KEPT']");
    public SelenideElement cartButton = $x("//*[@data-sendstatus='CART']");
    public SelenideElement unlinkButton = $x("//*[@data-sendstatus='UNLINK']");
}
