package com.zalando.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.zalando.steps.PropertiesHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    private String mainPageURL;

    private SelenideElement searchBar;
    private SelenideElement cookiesAcceptance;

    @Step("Open the main page")
    public void openBasePage(){
        open(mainPageURL);
        searchBar = $(By.name("q"));
        searchBar.shouldBe(Condition.visible);
        cookiesAcceptance = $(By.id("uc-btn-accept-banner"));
        cookiesAcceptance.shouldBe(Condition.visible, Duration.ofSeconds(10));
        cookiesAcceptance.click();
    }

    @Step("Type \"{0}\" in the search bar and click enter")
    public QueryResultsPage searchForAnItem(String input){
        searchBar.val(input).pressEnter();
        return new QueryResultsPage();
    }

    public BasePage(){
        this.mainPageURL = PropertiesHandler.properties.getProperty("basepage.URL");
    }
}
