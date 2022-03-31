package com.zalando.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import edu.emory.mathcs.backport.java.util.Arrays;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.zalando.steps.RandomStringPicker.getRandomString;

public class QueryResultsPage {

    private SelenideElement menClothesFilterButton;

    private SelenideElement colorToClick;
    private SelenideElement colourDropDownSave;
    private static ElementsCollection allColoursAvailable;

    private SelenideElement brandDropDown;
    private SelenideElement brandFilter;
    private SelenideElement brandDropDownSave;
    private static ElementsCollection allBrandsAvailable;

    private SelenideElement itemToBeSelected;

    @Step("Filter clothes to show men clothes only")
    public void showClothesForMen(){
        menClothesFilterButton = $(By.cssSelector("li > a[href$=\"/mezczyzni/\"]"));
        menClothesFilterButton.click();
    }

    @Step("Click colour form")
    public void clickClothingForm() {
        SelenideElement colourDropDown = $(By.xpath("//button[@aria-label = \"filtruj po Kolor\"]"));
        colourDropDown.click();
    }

    @Step("Choose random colour from the list of available colours")
    public static String getRandomColour(){
        allColoursAvailable = $$(By.xpath("//form[@name=\"collection-view-desktop-filter-colors\"]/div"));
        String c  = allColoursAvailable.texts().toString();
        String cc = c.substring(1, c.length()-1);
        List<String> colours = new ArrayList<>(Arrays.asList(cc.split("\n")));
        return getRandomString(colours);
    }

    @Step("Select clothing colour to: {0}")
    public void selectClothingColour(String colour){
        colorToClick = $(By.xpath("//span[text() = \""+colour+"\" ]")).scrollIntoView(true);
        colorToClick.click();

        colourDropDownSave = $(By.xpath("//button[@aria-label = \"dodaj filtr Kolor\"]")).scrollIntoView(true);
        colourDropDownSave.click();
    }

    @Step("Select a form to choose a brand")
    public void clickBrandForm() {
        brandDropDown = $(By.xpath("//button[@aria-label = \"filtruj po Marka\"]"));
        brandDropDown.click();
    }

    @Step("Get a brand name, randomly")
    public static String getRandomBrand(){
        allBrandsAvailable = $$(By.xpath("//form[@name=\"collection-view-desktop-filter-brands\"]/div"));
        String b = allBrandsAvailable.texts().toString();
        String bb = b.substring(1, b.length()-1);
        List<String> brands = new ArrayList<>(Arrays.asList(bb.split("\n")));
        return getRandomString(brands);
    }

    @Step("Select a randomly chosen brand: {0}")
    public void selectABrand(String brand){
        brandFilter = $(By.name("brand-filter-search"));
        brandFilter.sendKeys(brand);

        $(By.cssSelector("label")).shouldHave(Condition.text(brand)).click();

        brandDropDownSave = $(By.xpath("//button[@aria-label = \"dodaj filtr Marka\"]")).scrollIntoView(true);
        brandDropDownSave.click();
    }

    @Step("Select the first item from the list")
    public ProductPage selectAProduct(){
        itemToBeSelected = $(By.xpath("//div[@data-zalon-partner-target]/div[1]"));
        itemToBeSelected.click();
        return new ProductPage();
    }

}
