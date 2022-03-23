package com.zalando.testCases;

import com.zalando.assertions.Assertions;
import com.zalando.pages.CartPage;
import com.zalando.pages.MainPage;
import com.zalando.pages.ProductPage;
import com.zalando.pages.QueryResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddAProductToCartTest {

    @Test
    @Description("Performs searching for a product using filters and adding it to the cart," +
            "showing cart's contents using a screenshot")
    @Severity(value = SeverityLevel.NORMAL)
    public void addingProductToCartUsingFilters(){

        MainPage mainPage = new MainPage();
        mainPage.openMainPage();

        Assertions.mainPageAssertions();

        QueryResultsPage queryResultsPage = mainPage.searchForAnItem("jeans");
        queryResultsPage.showClothesForMen();
        queryResultsPage.selectClothingColour("Czarny");
        queryResultsPage.selectClothingBrand("Lee");

        Assertions.queryResultsPageAssertions();

        ProductPage productPage = queryResultsPage.selectAProduct("BROOKLYN");
        Assertions.beforeSelectionAssertions();
        productPage.selectSize("33x32");
        productPage.addItemToCart();

        Assertions.afterSelectionAssertions();

        CartPage cartPage = productPage.showCartPage();
        cartPage.takeScreenshot();

        Assertions.cartAssertions();
    }

    @AfterClass
    public void allureReport() throws IOException {
       Runtime.getRuntime().exec("allure serve allure-results\n");
    }
}
