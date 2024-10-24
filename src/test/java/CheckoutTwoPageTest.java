import org.example.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTwoPageTest extends BasePageTest {

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

        CheckoutTwoPage checkoutTwoPage = new CheckoutTwoPage(getDriver());
        checkoutTwoPage.finishCheckout();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-complete"));

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
        checkoutOnePage.fillForm("Jonathan","Joestar","PO16 7GZ");
        checkoutOnePage.continueCheckout();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-two"));

        CheckoutTwoPage checkoutTwoPage = new CheckoutTwoPage(getDriver());
        checkoutTwoPage.cancel();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory"));
    }

}
