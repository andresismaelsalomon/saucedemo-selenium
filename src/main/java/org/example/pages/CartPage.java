package org.example.pages;

import org.example.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CartPage extends BasePage {
    private final By cartList = By.xpath("(//*[@class=\"cart_list\"])[1]");

    private final By noOfItemsInCart = By.xpath("(//*[@class=\"fa-layers-counter shopping_cart_badge\"])[1]");

    private final By buttonContinueShopping=By.xpath("(//*[@href=\"./inventory.html\"])[2]");

    private final By buttonCheckout=By.xpath("(//*[@href=\"./checkout-step-one.html\"])[1]");

    private List<CartItem> items = new ArrayList<>();

    public CartPage(WebDriver driver, int quantity) {
        super(driver);
        initializeItems(quantity);
    }

    public List<CartItem> initializeItems(int quantity) {
        if (items.isEmpty()) {
            for (int i = 0; i < quantity; i++) {
                items.add(new CartItem(
                        getDriver(),
                        By.xpath("(//*[@class=\"cart_quantity\"])[" + (i + 1) + "]"),
                        By.xpath("(//*[@class=\"cart_item_label\"])[" + (i + 1) + "]"),
                        By.xpath("(//*[@class=\"inventory_item_desc\"])[" + (i + 1) + "]"),
                        By.xpath("(//*[@class=\"inventory_item_price\"])[" + (i + 1) + "]"),
                        By.xpath("(//*[@class=\"btn_primary btn_inventory\"])[" + (i + 1) + "]"),
                        By.xpath("(//*[@class=\"btn_secondary cart_button\"])[" + (i + 1) + "]")
                ));
            }
        }
        return items;
    }

    public void removeItemFromCart(String title) {
        items.stream()
                .filter(i -> i.getTitle().toLowerCase().contains(title.toLowerCase()))
                .findFirst()
                .ifPresent(item -> {
                    System.out.println("Item removed: " + item.getTitle());
                    waitForElementToBeClickable(item.getButtonRemoveFromCart()).click();
                });
    }

    public void goToURL() {
        getDriver().get("https://www.saucedemo.com/v1/");
    }

    public Integer getNoOfItemsInCart() {
        Integer number = Integer.valueOf(waitForElementToBeClickable(noOfItemsInCart).getText());
        System.out.println("number:"+number);
        return number;
    }

    public boolean getRemovedCartItem(int index) {
        WebElement webElement = waitForElementToBePresent(By.xpath("(//*[@class='removed_cart_item'])[" + index + "]"));
        return waitForElementToBeInvisible(webElement);
    }

    public void continueShopping() {
        waitForElementToBeClickable(buttonContinueShopping).click();
    }

    public void checkout(){
        waitForElementToBeClickable(buttonCheckout).click();
    }
}
