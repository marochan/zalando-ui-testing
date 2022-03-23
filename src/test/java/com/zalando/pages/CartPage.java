package com.zalando.pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.LocalDateTime;
public class CartPage {

    @Step("Take screenshot showing product added to the cart")
    public byte[] takeScreenshot(){
        return getScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] getScreenshot(){
        LocalDateTime date = LocalDateTime.now();
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
