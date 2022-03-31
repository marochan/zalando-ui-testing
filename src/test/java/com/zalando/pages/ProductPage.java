package com.zalando.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.zalando.steps.RandomStringPicker.getRandomString;

public class ProductPage {
    private SelenideElement sizeDropDown;
    private SelenideElement addToCartButton;
    private SelenideElement getCartButton;
    private static ElementsCollection sizesAvailable;

    @Step("Click on dropdown with sizes available")
    public boolean clickOnSizesForm(){
        sizeDropDown = $(By.id("picker-trigger")).scrollIntoView(true);
        if (sizeDropDown.has(Condition.disabled)){
            return true;
        } else {
            sizeDropDown.click();
            $(By.name("size-picker-form")).shouldBe(Condition.visible);
            return false;
        }
    }

    @Step("Select a size available")
    public static String getRandomSize(){
        sizesAvailable = $$(By.xpath("//label/span/div/span[1]"));
        List<String> list = new ArrayList<>();
        for(SelenideElement se : sizesAvailable){
            if(se.has(Condition.enabled)){
                list.add(se.getText());
            }
        }
        String size = getRandomString(list);
        return size;
    }

    @Step("Choose randomly chosen size: {0} from the available ones")
    public void selectSize(String size)throws Exception{
        SelenideElement droppable = $(By.xpath("//label/span/div/span[1][contains(text(), \"" +size+ "\")]"));
        droppable.click();
        if($(By.cssSelector("div[tabindex=\"-1\"][role = \"modal\"]"))
                .has(Condition.and("Not available", Condition.exist, Condition.visible))){
            System.out.println(WebDriverRunner.getWebDriver().getCurrentUrl());
            throw new Exception("Size not available!");
        }
    }

    @Step("Add item to the cart")
    public void addItemToCart(){
        addToCartButton = $(By.xpath("//button[@aria-label = \"Dodaj do koszyka\"]"));
        addToCartButton.shouldBe(Condition.visible);
        addToCartButton.click();
    }

    @Step("Open cart with summary showing added product")
    public CartPage showCartPage(){
        getCartButton = $(By.xpath("//a[@title=\"Koszyk\"]"));
        getCartButton.click();
        return new CartPage();
    }
}
