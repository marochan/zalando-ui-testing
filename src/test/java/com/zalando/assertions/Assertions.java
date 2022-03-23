package com.zalando.assertions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class Assertions {

    public static void mainPageAssertions() {
        Assert.assertEquals(title(), "Buty i odzież online na Zalando. Moda | darmowa* dostawa i zwrot", "The title of the page is different than expected");
        $(By.xpath("//img[@alt=\"Zalando\"]")).shouldBe(Condition.visible);
    }

    public static void queryResultsPageAssertions() {
        Assert.assertEquals(title(), "Jeans - Kup online | Katalog | Zalando", "The title is different from the one expected");
        Assert.assertEquals($(By.xpath("//a[@href=\"/mezczyzni-home/\"]")).getCssValue("background-color"), ("rgba(26, 26, 26, 1)"), "The background colour of element is different than expected");
        Assert.assertEquals($(By.xpath("//span[text()=\"Kolor\"]/span")).getText(), "1", "The value on the element differs from the expected one");
        Assert.assertEquals($(By.xpath("//span[text()=\"Marka\"]/span")).getText(), "1", "The value on the element differs from the expected one");
    }

    public static void beforeSelectionAssertions(){
        Assert.assertTrue($(By.xpath("//div[@role=\"img\" and @class = \"_0xLoFW\"]")).has(Condition.visible));
        Assert.assertTrue($(By.id("picker-trigger")).has(Condition.visible));
        Assert.assertTrue($(By.id("picker-trigger")).has(Condition.exactText("Wybierz rozmiar")));
    }

    public static void afterSelectionAssertions(){
        SelenideElement cart = $(By.xpath("//a[@title=\"Koszyk\"]/div/span"));
        cart.shouldBe(Condition.visible, Duration.ofSeconds(2));
        Assert.assertEquals(cart.getText(), "1", "The value stored by the element is different from the one expected");
        Assert.assertEquals(cart.getCssValue("background-color"), "rgba(255, 105, 0, 1)");
    }

    public static void cartAssertions(){
        Assert.assertEquals($(By.cssSelector("select")).getText(), "1", "The actual number held by element is different from the ");
        Assert.assertTrue($(By.xpath("//div[@class=\"z-1-button__content\"]")).has(Condition.visible));
        Assert.assertTrue($(By.xpath("//dl[@data-id=\"delivery-estimation\"]")).exists());
    }
}
