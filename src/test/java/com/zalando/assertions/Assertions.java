package com.zalando.assertions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class Assertions {

    private static SoftAssert softAssert = new SoftAssert();

    public static void basePageAssertions() {
        softAssert.assertEquals(title(), "Buty i odzież online na Zalando. Moda | darmowa* dostawa i zwrot", "The title of the page is different than expected");
        //verifying if the zalando logo is visible
        $(By.xpath("//img[@alt=\"Zalando\"]")).shouldBe(Condition.visible);
        softAssert.assertAll();
    }

    public static void queryResultsPageAssertions(String clothingType) {
        softAssert.assertEquals(title(), clothingType + " - Kup online | Katalog | Zalando", "The title is different from the one expected");
        softAssert.assertEquals($(By.xpath("//a[@href=\"/mezczyzni-home/\"]")).getCssValue("background-color"), ("rgba(26, 26, 26, 1)"), "The background colour of element is different than expected");
        softAssert.assertEquals($(By.xpath("//span[text()=\"Kolor\"]/span")).getText(), "1", "The value on the element describing amount of colours chosen differs from the expected one");
        softAssert.assertEquals($(By.xpath("//span[text()=\"Marka\"]/span")).getText(), "1", "The value on the element describing amount of brands chosen differs from the expected one");
        softAssert.assertAll();
    }

    public static void beforeSelectionAssertions(){
        //veryfying that the elemnt showing stars for certain product is visible
        softAssert.assertTrue($(By.xpath("//div[@role=\"img\" and @class = \"_0xLoFW\"]")).has(Condition.visible));
        //veryfying that dropdown with sizes is visible
        softAssert.assertTrue($(By.id("picker-trigger")).has(Condition.visible));
        //veryfying that before clicking the element has either of the expected texts
        $(By.id("picker-trigger")).has(Condition.or("Text",
                Condition.exactText("One size"), Condition.exactText("Wybierz rozmiar")));
        softAssert.assertAll();
    }

    public static void afterSelectionAssertions(){
        //veryfying that the element directing to cart is visible
        SelenideElement cart = $(By.xpath("//a[@title=\"Koszyk\"]/div/span")).scrollIntoView(true);
        cart.shouldBe(Condition.visible, Duration.ofSeconds(2));
        //veryfying that after adding one product the icon on cart element shows exactly "1"
        softAssert.assertEquals(cart.getText(), "1", "The value stored by the element is different from the one expected");
        //veryfying that element has certain colour
        softAssert.assertEquals(cart.getCssValue("background-color"), "rgba(255, 105, 0, 1)");
        softAssert.assertAll();
    }

    public static void cartAssertions(){
        //veryfying that the element storing information about amount of product to be sold shows "1"
        softAssert.assertEquals($(By.cssSelector("select")).getText(), "1", "The actual number held by element is different from the expected");
        //veryfying that element redirecting to order completion is visible
        softAssert.assertTrue($(By.xpath("//div[text() = \"Przejdź do kasy\"]")).has(Condition.visible));
        //veryfying that element storing information about arrival date of package is visible
        $(By.xpath("//dl[@data-id=\"delivery-estimation\"]")).shouldBe(Condition.visible);
        softAssert.assertAll();
    }
}
