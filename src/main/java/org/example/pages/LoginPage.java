package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By inputUsername = By.xpath("(//*[@id=\"user-name\"])[1]");

    private final By inputPassword = By.xpath("(//*[@id=\"password\"])[1]");

    private final By buttonLogIn = By.xpath("(//*[@id=\"login-button\"])[1]");

    private final By textError= By.xpath("//*[@id=\"login_button_container\"]/div/form/h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logIn() {
        waitForElementToBeClickable(inputUsername).sendKeys(Users.standard_user.toString());
        waitForElementToBeClickable(inputPassword).sendKeys(Pass.secret_sauce.toString());
        waitForElementToBeClickable(buttonLogIn).click();
    }

    public void logIn(String user, String pass) {
        waitForElementToBeClickable(inputUsername).sendKeys(user);
        waitForElementToBeClickable(inputPassword).sendKeys(pass);
        waitForElementToBeClickable(buttonLogIn).click();
    }

    public void goToURL() {
        getDriver().get("https://www.saucedemo.com/v1/");
    }

    public String getTextError() {
        return waitForElementToBeClickable(textError).getText();
    }
}
