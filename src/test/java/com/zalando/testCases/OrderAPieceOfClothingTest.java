package com.zalando.testCases;

import com.zalando.pages.CartPage;
import com.zalando.pages.MainPage;
import com.zalando.pages.ProductPage;
import com.zalando.pages.QueryResultsPage;
import org.testng.annotations.Test;


public class OrderAPieceOfClothingTest {

    @Test
    public void orderAPieceOfClothing() throws InterruptedException{

        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        QueryResultsPage queryResultsPage = mainPage.searchForAnItem("jeans");


        queryResultsPage.showClothesForMen();
        queryResultsPage.selectClothingColour("Czarny");
        queryResultsPage.selectClothingBrand("Lee");
        ProductPage productPage = queryResultsPage.selectAProduct("BROOKLYN");

        productPage.selectSize("33x32");
        productPage.addItemToCart();

        CartPage cartPage = productPage.showCartPage();
        cartPage.takeScreenshot();

    }
}
