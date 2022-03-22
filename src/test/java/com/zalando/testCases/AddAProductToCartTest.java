package com.zalando.testCases;

import com.zalando.pages.CartPage;
import com.zalando.pages.MainPage;
import com.zalando.pages.ProductPage;
import com.zalando.pages.QueryResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class AddAProductToCartTest {

    @Test
    @Description("Performs searching for a product using filters and adding it to the cart," +
            "showing cart's contents using a screenshot")
    @Severity(value = SeverityLevel.NORMAL)
    public void addingProductToCartUsingFilters() throws InterruptedException{

        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        Assert.assertEquals(title(), "Buty i odzież online na Zalando. Moda | darmowa* dostawa i zwrot", "The title of the page is different than expected");

        QueryResultsPage queryResultsPage = mainPage.searchForAnItem("jeans");
        Assert.assertEquals(title(), "Jeans - Kup online | Katalog | Zalando", "The title is different from the one expected");

        queryResultsPage.showClothesForMen();
        queryResultsPage.selectClothingColour("Czarny");
        queryResultsPage.selectClothingBrand("Lee");
        ProductPage productPage = queryResultsPage.selectAProduct("BROOKLYN");

        productPage.selectSize("33x32");
        productPage.addItemToCart();

        CartPage cartPage = productPage.showCartPage();
        cartPage.takeScreenshot();

    }

    @AfterClass
    public void allureReport() throws IOException {
        Runtime.getRuntime().exec("allure serve allure-results\n");
    }
}
