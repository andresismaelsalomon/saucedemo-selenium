package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOnePage extends BasePage {

    public CheckoutOnePage(WebDriver driver) {
        super(driver);
    }

    private final By firstNameLocator = By.xpath("(//*[@id=\"first-name\"])[1]");
    private final By lastNameLocator = By.xpath("(//*[@id=\"last-name\"])[1]");
    private final By zipCodeLocator = By.xpath("(//*[@id=\"postal-code\"])[1]");
    private final By buttonCancel = By.xpath("(//*[@href=\"./cart.html\"])[2]");
    private final By buttonContinue = By.xpath("(//*[@type=\"submit\"])[1]");

    private final By error=By.xpath("(//*[@data-test=\"error\"])[1]");

    public void fillForm(String firstName,String lastName, String zipCode){
        waitForElementToBeClickable(firstNameLocator).sendKeys(firstName);
        waitForElementToBeClickable(lastNameLocator).sendKeys(lastName);
        waitForElementToBeClickable(zipCodeLocator).sendKeys(zipCode);
    }

    public void cancel(){
        waitForElementToBeClickable(buttonCancel).click();
    }

    public void continueCheckout(){
        waitForElementToBeClickable(buttonContinue).click();
    }

    public String getErrorMessage() {
        return waitForElementToBeClickable(error).getText();
    }
}
