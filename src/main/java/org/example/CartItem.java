package org.example;


import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartItem extends BasePage {
    private WebDriver driver;
    private final By cartQuantity;
    private final By title;
    private final By desc;
    private final By price;
    private final By buttonAddToCart;
    private final By buttonRemoveFromCart;

    public CartItem(WebDriver driver, By cartQuantity,By title, By desc, By price, By buttonAddToCart, By buttonRemoveFromCart) {
        super(driver);
        this.cartQuantity=cartQuantity;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.buttonAddToCart = buttonAddToCart;
        this.buttonRemoveFromCart=buttonRemoveFromCart;
    }
    public String getCartQuantity(){return waitForElementToBeClickable(cartQuantity).getText();}

    public String getTitle() {
        return waitForElementToBeClickable(title).getText();
    }

    public String getDesc() {
        return waitForElementToBeClickable(desc).getText();
    }

    public String getPrice() {
        String priceText = waitForElementToBeClickable(price).getText();
        return priceText.replaceAll("[$€]", "");
    }

    public By getButtonAddToCart() {
        return buttonAddToCart;
    }

    public By getButtonRemoveFromCart() {
        return buttonRemoveFromCart;
    }
}

