import actionDriver.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.AppointmentPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BrowserUtils;


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
    }

    @Test (priority = 1)
    public void loginEmptyUsername() {
        loginPage.enterUsername("");
        loginPage.enterPassword(validPassword);
        loginPage.clickLogin();
    }

    @Test (priority = 2)
    public void loginEmptyPassword() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword("");
        loginPage.clickLogin();
    }

    @Test (priority = 3)
    public void loginInvalidAuth() {
        loginPage.enterUsername(invalidUserName);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickLogin();
    }

    @Test (priority = 4)
    public void loginInvalidUsername() {
        loginPage.enterUsername(invalidUserName);
        loginPage.enterPassword(validPassword);
        loginPage.clickLogin();
    }

    @Test (priority = 5)
    public void loginInvalidPassword() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickLogin();
    }

    @Test (priority = 6)
    public void loginValid() {
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickLogin();
    }

    @Test (priority = 7)
    public void logout() {
        appointmentPage.openMenu();
        appointmentPage.clickLogout();
    }
}
