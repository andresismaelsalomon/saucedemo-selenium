import org.example.Item;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

public class InventoryPageTest extends BasePageTest {

    @Test(testName = "01 - addRandomItemTest")
    public void addRandomItemTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addRandomItems();
        Assert.assertEquals((int) inventoryPage.getNoOfItemsInCart(), 2);
    }

    @Test(testName = "02 - addSpecificItemTest")
    public void addSpecificItemTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addItemToCart("backpack");
        inventoryPage.addItemToCart("shirt");
        Assert.assertTrue(inventoryPage.getNoOfItemsInCart() > 1);
    }

    @Test(testName = "03 - removeSpecificItemTest")
    public void removeSpecificItemTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addItemToCart("backpack");
        inventoryPage.addItemToCart("shirt");
        Assert.assertTrue(inventoryPage.getNoOfItemsInCart() > 1);
        inventoryPage.removeItemFromCart("backpack");
        Assert.assertTrue(inventoryPage.getNoOfItemsInCart() < 2);
    }

    @Test(testName = "04 - sortByAZTest")
    public void sortByAZTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.setSelectOrder("za");
        inventoryPage.setSelectOrder("az");
        List<Item> items2 = inventoryPage.initializeItems();

        List<String> actualTitles = items2.stream()
                .map(Item::getTitle)  // Assuming getTitleText() returns the title as a string
                .toList();

        Assert.assertTrue(
                IntStream.range(0, actualTitles.size() - 1)
                        .allMatch(i -> actualTitles.get(i).compareTo(actualTitles.get(i + 1)) <= 0),
                "The items are not sorted in A-Z order."
        );
    }

    @Test(testName = "05 - sortByZATest")
    public void sortByZATest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.setSelectOrder("az");
        inventoryPage.setSelectOrder("za");
        List<Item> items2 = inventoryPage.initializeItems();

        List<String> actualTitles = items2.stream()
                .map(Item::getTitle)  // Assuming getTitleText() returns the title as a string
                .toList();

        Assert.assertTrue(
                IntStream.range(0, actualTitles.size() - 1)
                        .allMatch(i -> actualTitles.get(i).compareTo(actualTitles.get(i + 1)) >= 0),
                "The items are not sorted in Z-A order."
        );
    }

    @Test(testName = "06 - sortByLowPriceTest")
    public void sortByLowPriceTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.setSelectOrder("hilo");
        inventoryPage.setSelectOrder("lohi");
        List<Item> items2 = inventoryPage.initializeItems();

        // Extract prices as doubles for comparison
        List<Double> actualPrices = items2.stream()
                .map(item -> Double.parseDouble(item.getPrice().replace("$", "").trim())) // Assuming getPriceText() returns the price as a string with a "$"
                .toList();

        // Assert that the prices are sorted in ascending order
        Assert.assertTrue(
                IntStream.range(0, actualPrices.size() - 1)
                        .allMatch(i -> actualPrices.get(i) <= actualPrices.get(i + 1)),
                "The items are not sorted by price in ascending order."
        );
    }


    @Test(testName = "07 - sortByHighPriceTest")
    public void sortByHighPriceTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.setSelectOrder("lohi");
        inventoryPage.setSelectOrder("hilo");
        List<Item> items2 = inventoryPage.initializeItems();

        // Extract prices as doubles for comparison
        List<Double> actualPrices = items2.stream()
                .map(item -> Double.parseDouble(item.getPrice().replace("$", "").trim())) // Assuming getPriceText() returns the price as a string with a "$"
                .toList();

        // Assert that the prices are sorted in descending order
        Assert.assertTrue(
                IntStream.range(0, actualPrices.size() - 1)
                        .allMatch(i -> actualPrices.get(i) >= actualPrices.get(i + 1)),
                "The items are not sorted by price in descending order."
        );
    }


}
