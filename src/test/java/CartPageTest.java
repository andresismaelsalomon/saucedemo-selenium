import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BasePageTest {

    @Test(testName = "01 - removeItemFromCartTest")
    public void removeItemFromCartTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addItemToCart("backpack");
        Assert.assertEquals((int) inventoryPage.getNoOfItemsInCart(), 1);

        inventoryPage.goToCart();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("cart"));

        CartPage cartPage = new CartPage(getDriver(), 1);
        cartPage.removeItemFromCart("backpack");
        Assert.assertTrue(cartPage.getRemovedCartItem(1));

    }

    @Test(testName = "02 - continueShoppingTest")
    public void continueShoppingTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addItemToCart("backpack");
        inventoryPage.addItemToCart("shirt");
        Assert.assertTrue(inventoryPage.getNoOfItemsInCart() > 1);

        inventoryPage.goToCart();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("cart"));

        CartPage cartPage = new CartPage(getDriver(), 1);
        cartPage.continueShopping();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory"));
    }

    @Test(testName = "03 - checkoutTest")
    public void checkoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addItemToCart("backpack");
        inventoryPage.addItemToCart("shirt");
        Assert.assertTrue(inventoryPage.getNoOfItemsInCart() > 1);

        inventoryPage.goToCart();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("cart"));

        CartPage cartPage = new CartPage(getDriver(), 1);
        cartPage.checkout();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-one"));
    }

}
