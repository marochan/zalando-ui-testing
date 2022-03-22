package com.zalando.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
public class QueryResultsPage {

    private SelenideElement menClothesFilterButton;

    private SelenideElement colourDropDown;
    private SelenideElement colorToClick;
    private SelenideElement colourDropDownSave;

    private SelenideElement brandDropDown;
    private SelenideElement brandFilter;
    private SelenideElement brandDropDownSave;

    private SelenideElement itemToBeSelected;
    @Step("Filter clothes to show men clothes only")
    public void showClothesForMen(){
        menClothesFilterButton = $(By.cssSelector("li > a[href$=\"/mezczyzni/\"]"));
        menClothesFilterButton.click();
    }

    @Step("Filter clothing colour to be {0}}")
    public void selectClothingColour(String colour){
        colourDropDown = $(By.xpath("//button[@aria-label = \"filtruj po Kolor\"]"));
        colourDropDown.click();

        colorToClick = $(By.xpath("//span[text() = \""+colour+"\" ]")).scrollIntoView(true);
        colorToClick.click();

        colourDropDownSave = $(By.xpath("//button[@aria-label = \"dodaj filtr Kolor\"]")).scrollIntoView(true);
        colourDropDownSave.click();
    }

    @Step("filter clothing brand to be {0}")
    public void selectClothingBrand(String brand) throws InterruptedException{
        brandDropDown = $(By.xpath("//button[@aria-label = \"filtruj po Marka\"]"));
        brandDropDown.click();

        brandFilter = $(By.name("brand-filter-search"));
        brandFilter.sendKeys(brand);

        $(By.cssSelector("label")).shouldHave(Condition.exactText(brand)).click();

        Thread.sleep(4000);
        brandDropDownSave = $(By.xpath("//button[@aria-label = \"dodaj filtr Marka\"]")).scrollIntoView(true);
        brandDropDownSave.click();
    }

    @Step("Select an item which has {0} in its name")
    public ProductPage selectAProduct(String name){
        itemToBeSelected = $(By.xpath("//h3[contains(text(),\"" + name + "\" )]")).scrollIntoView(true);
        itemToBeSelected.click();
        return new ProductPage();
    }
}
