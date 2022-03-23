package com.zalando.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final String URL = "https://www.zalando.pl";
    private SelenideElement searchBar;
    private SelenideElement cookiesAcceptance;

    @Step("Open the main page")
    public void openMainPage(){
        open(URL);
        searchBar = $(By.name("q"));
        searchBar.shouldBe(Condition.visible);
        cookiesAcceptance = $(By.id("uc-btn-accept-banner"));
        cookiesAcceptance.click();
    }

    @Step("Type \"{0}\" in the search bar and click enter")
    public QueryResultsPage searchForAnItem(String input){
        searchBar.val(input).pressEnter();
        return new QueryResultsPage();
    }
}
