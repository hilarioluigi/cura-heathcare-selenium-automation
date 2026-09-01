package test;

import actionDriver.Action;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.AppointmentPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BrowserUtils;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class LoginTest {
    ChromeDriver driver;
    Action action;
    HomePage homePage;
    LoginPage loginPage;
    AppointmentPage appointmentPage;

    static String validUsername = "John Doe";
    static String validPassword = "ThisIsNotAPassword";
    String invalidUserName = "Fake John";
    String invalidPassword = "FakePass";
    String actualErrorMsg;
    String currentUrl;
    String expectedErrorMsg = "Login failed! Please ensure the username and password are valid.";

    @BeforeClass
    public void setup(){
        driver = BrowserUtils.createChromeDriver();
        action = new Action(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        appointmentPage = new AppointmentPage(driver);

        action.maximize();
        action.openWeb();
        homePage.clickMakeAppointment();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        File screenShot = driver.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenShot, new File("./src/test/java/resources/screenshots/" + result.getName() + System.currentTimeMillis() + ".jpg"));
    }

    @AfterClass
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        action.quit();
    }

    @Test (priority = 0)
    public void loginEmptyAuth() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        actualErrorMsg = loginPage.assertErrorMsg();
        Assert.assertEquals(actualErrorMsg,expectedErrorMsg);
    }

    @Test (priority = 1)
    public void loginEmptyUsername() {
        loginPage.enterUsername("");
        loginPage.enterPassword(validPassword);
        loginPage.clickLogin();
        actualErrorMsg = loginPage.assertErrorMsg();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Test (priority = 2)
    public void loginEmptyPassword() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword("");
        loginPage.clickLogin();
        actualErrorMsg = loginPage.assertErrorMsg();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Test (priority = 3)
    public void loginInvalidAuth() {
        loginPage.enterUsername(invalidUserName);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickLogin();
        actualErrorMsg = loginPage.assertErrorMsg();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Test (priority = 4)
    public void loginInvalidUsername() {
        loginPage.enterUsername(invalidUserName);
        loginPage.enterPassword(validPassword);
        loginPage.clickLogin();
        actualErrorMsg = loginPage.assertErrorMsg();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Test (priority = 5)
    public void loginInvalidPassword() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickLogin();
        actualErrorMsg = loginPage.assertErrorMsg();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Test (priority = 6)
    public void loginValid(){
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        currentUrl = driver.getCurrentUrl();
        loginPage.clickLogin();
        action.waitUrlChanges(currentUrl, 15);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/#appointment"));
    }

    @Test (priority = 7)
    public void logout() {
        appointmentPage.openMenu();
        currentUrl = driver.getCurrentUrl();
        appointmentPage.clickLogout();
        action.waitUrlChanges(currentUrl, 15);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertEquals(currentUrl, "https://katalon-demo-cura.herokuapp.com/");
    }
}
