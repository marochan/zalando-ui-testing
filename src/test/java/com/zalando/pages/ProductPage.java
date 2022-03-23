package com.zalando.pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private SelenideElement sizeDropDown;
    private SelenideElement addToCartButton;
    private SelenideElement getCartButton;

    @Step("Choose size: {0} for selected item")
    public void selectSize(String size){
        sizeDropDown = $(By.id("picker-trigger")).scrollIntoView(true);
        sizeDropDown.click();

        $(By.xpath("//span[contains(text(),\"" + size + "\" )]")).scrollIntoView(true).click();
    }

    @Step("Add item to the cart")
    public void addItemToCart(){
        addToCartButton = $(By.xpath("//button[@aria-label = \"Dodaj do koszyka\"]")).scrollIntoView(true);
        addToCartButton.click();
    }

    @Step("Open cart with summary showing added product")
    public CartPage showCartPage(){
        getCartButton = $(By.xpath("//a[@title=\"Koszyk\"]"));
        getCartButton.click();
        return new CartPage();
    }
}
