package com.zalando.testCases;

import com.zalando.enums.ClothingTypes;
import com.zalando.pages.CartPage;
import com.zalando.pages.BasePage;
import com.zalando.pages.ProductPage;
import com.zalando.pages.QueryResultsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.zalando.assertions.Assertions.*;


public class AddAProductToCartTest extends BaseTest{

    @Test
    @Description("Performs searching for a product using filters and adding it to the cart," +
            "showing cart's contents using a screenshot")
    @Severity(value = SeverityLevel.NORMAL)
    public void addingProductToCartUsingFilters() throws Exception{
        BasePage basePage = new BasePage();
        basePage.openBasePage();

        basePageAssertions();

        String clothingType = ClothingTypes.getRandom().toString();
        QueryResultsPage queryResultsPage = basePage.searchForAnItem(clothingType);
        queryResultsPage.showClothesForMen();

        queryResultsPage.clickClothingForm();
        String colour = QueryResultsPage.getRandomColour();
        queryResultsPage.selectClothingColour(colour);

        queryResultsPage.clickBrandForm();
        String brand = QueryResultsPage.getRandomBrand();
        queryResultsPage.selectABrand(brand);

        queryResultsPageAssertions(clothingType);

        ProductPage productPage = queryResultsPage.selectAProduct();
        beforeSelectionAssertions();
        boolean isSizesFormAvailable =  productPage.clickOnSizesForm();
        if(isSizesFormAvailable){
            productPage.addItemToCart();
        } else {
            String size = ProductPage.getRandomSize();
            productPage.selectSize(size);
            productPage.addItemToCart();
        }
        afterSelectionAssertions();

        CartPage cartPage = productPage.showCartPage();
        cartPage.takeScreenshot();

        cartAssertions();
    }



}
