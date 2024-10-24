import org.example.pages.LoginPage;
import org.example.pages.Pass;
import org.example.pages.Users;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePageTest{

    @Test(testName = "01 - Login Test")
    public void logInTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn();
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(),"https://www.saucedemo.com/v1/inventory.html");
    }

    @Test(testName = "02 - Login with wrong username Test")
    public void logInWithWrongUsernameTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn("!#@$a", Pass.secret_sauce.toString());
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "03 - Login with wrong password Test")
    public void logInWithWrongPasswordTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn(Users.standard_user.toString(),"!#@$a");
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "04 - Login with no username Test")
    public void logInWithNoUsernameTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn("",Pass.secret_sauce.toString());
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Username is required");
    }

    @Test(testName = "05 - Login with no password Test")
    public void logInWithNoPasswordTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn(Users.standard_user.toString(),"");
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Password is required");
    }

    @Test(testName = "06 - Login with wrong username and password Test")
    public void logInWithWrongUsernameAndPasswordTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn("!#@$a","!#@$a");
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "07 - Login with no username or password Test")
    public void logInWithNoUsernameOrPasswordTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn("","");
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Username is required");
    }

    @Test(testName = "08 - Login with locked_out_user Test")
    public void logInLockedOutUserTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn(Users.locked_out_user.toString(),Pass.secret_sauce.toString());
        Assert.assertEquals(loginPage.getTextError(),"Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(testName = "09 - Login with problem_user Test")
    public void logInProblemUserTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn(Users.problem_user.toString(),Pass.secret_sauce.toString());
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(),"https://www.saucedemo.com/v1/inventory.html");
    }

    @Test(testName = "10 - Login with performance_glitch_user Test")
    public void logInPerformanceGlitchUserTest(){
        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.goToURL();
        loginPage.logIn(Users.performance_glitch_user.toString(),Pass.secret_sauce.toString());
        Assert.assertEquals(loginPage.getDriver().getCurrentUrl(),"https://www.saucedemo.com/v1/inventory.html");
    }

}
