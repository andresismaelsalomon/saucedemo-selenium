package org.example.pages;

import org.example.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class InventoryPage extends BasePage {
    private final By anchorCart = By.xpath("(//*[@class=\"shopping_cart_link fa-layers fa-fw\"])[1]");

    private final By noOfItemsInCart = By.xpath("(//*[@class=\"fa-layers-counter shopping_cart_badge\"])[1]");

    By selectOrder=By.xpath("(//*[@class=\"product_sort_container\"])[1]");
    Select select = new Select(waitForElementToBeClickable(selectOrder));

    private List<Item> items = new ArrayList<>();

    public InventoryPage(WebDriver driver) {
        super(driver);
        items=initializeItems();
    }

    public void addRandomItems() {
        Random random = new Random();
        Set<Integer> selectedIndices = new HashSet<>();
        int itemsToSelect = 2;

        while (selectedIndices.size() < itemsToSelect) {
            int r = random.nextInt(5);
            if (selectedIndices.add(r)) {
                System.out.println(items.get(r).getTitle());
                waitForElementToBeClickable(items.get(r).getButtonAddToCart()).click();
            }
        }
    }

    public List<Item> initializeItems() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            items.add(new Item(
                    getDriver(),
                    By.xpath("(//*[@class=\"inventory_item_name\"])[" + (i + 1) + "]"),
                    By.xpath("(//*[@class=\"inventory_item_desc\"])[" + (i + 1) + "]"),
                    By.xpath("(//*[@class=\"inventory_item_price\"])[" + (i + 1) + "]"),
                    By.xpath("(//*[@class=\"btn_primary btn_inventory\"])[" + (i + 1) + "]"),
                    By.xpath("(//*[@class=\"btn_secondary btn_inventory\"])[" + (i + 1) + "]")
            ));
        }
        return items;
    }

    public void addItemToCart(String title) {
        items.stream()
                .filter(i -> i.getTitle().toLowerCase().contains(title.toLowerCase()))
                .findFirst()
                .ifPresent(item -> {
                    System.out.println("Item added: " + item.getTitle());
                    waitForElementToBeClickable(item.getButtonAddToCart()).click();
                });
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
      return Integer.valueOf(waitForElementToBeClickable(noOfItemsInCart).getText());
    }

    public By getAnchorCart() {
        return anchorCart;
    }

    public void setSelectOrder(String value){
        select(select,value);
    }

    public void goToCart() {
        waitForElementToBeClickable(By.xpath("(//*[@class=\"shopping_cart_container\"])[1]")).click();
    }
}
