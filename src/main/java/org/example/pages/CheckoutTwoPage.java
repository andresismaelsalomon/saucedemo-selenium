package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutTwoPage extends BasePage {

    public CheckoutTwoPage(WebDriver driver) {
        super(driver);
    }

    private final By cancelLocator = By.xpath("(//*[@href=\"./inventory.html\"])[2]");
    private final By finishLocator = By.xpath("(//*[@href=\"./checkout-complete.html\"])[1]");

    public void cancel() {
        waitForElementToBeClickable(cancelLocator).click();
    }

    public void finishCheckout() {
        waitForElementToBeClickable(finishLocator).click();
    }
}
