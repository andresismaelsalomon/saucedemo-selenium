import org.example.pages.CartPage;
import org.example.pages.CheckoutOnePage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutOnePageTest extends BasePageTest {

    @Test(testName = "01 - continueCheckoutTest")
    public void continueCheckoutTest(){
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

        CheckoutOnePage checkoutOnePage=new CheckoutOnePage(getDriver());
        checkoutOnePage.fillForm("Jonathan","Joestar","PO16 7GZ");
        checkoutOnePage.continueCheckout();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-two"));

    }

    @Test(testName = "02 - cancelCheckoutTest")
    public void cancelCheckoutTest(){
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

        CheckoutOnePage checkoutOnePage=new CheckoutOnePage(getDriver());
        checkoutOnePage.cancel();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("cart"));
    }


    @Test(testName = "03 - errorFirstNameTest")
    public void errorFirstNameTest(){
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

        CheckoutOnePage checkoutOnePage=new CheckoutOnePage(getDriver());
        checkoutOnePage.fillForm("","Joestar","PO16 7GZ");
        checkoutOnePage.continueCheckout();

        Assert.assertTrue(checkoutOnePage.getErrorMessage().equalsIgnoreCase("Error: First Name is required"));
    }

    @Test(testName = "04 - errorLastNameTest")
    public void errorLastNameTest(){
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

        CheckoutOnePage checkoutOnePage=new CheckoutOnePage(getDriver());
        checkoutOnePage.fillForm("Jonathan","","PO16 7GZ");
        checkoutOnePage.continueCheckout();

        Assert.assertTrue(checkoutOnePage.getErrorMessage().equalsIgnoreCase("Error: Last Name is required"));
    }

    @Test(testName = "05 - errorZipCodeTest")
    public void errorZipCodeTest(){
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

        CheckoutOnePage checkoutOnePage=new CheckoutOnePage(getDriver());
        checkoutOnePage.fillForm("Jonathan","Joestar","");
        checkoutOnePage.continueCheckout();

        Assert.assertTrue(checkoutOnePage.getErrorMessage().equalsIgnoreCase("Error: Postal Code is required"));
    }
}
