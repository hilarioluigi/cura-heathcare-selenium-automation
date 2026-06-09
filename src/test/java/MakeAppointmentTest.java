import actionDriver.Action;
import actionDriver.WaitAction;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utils.BrowserUtils;

import java.io.File;
import java.io.IOException;

public class MakeAppointmentTest {
    ChromeDriver driver;
    Action action;
    WaitAction waitAction;

    HomePage homePage;
    LoginPage loginPage;
    AppointmentPage appointmentPage;
    AppointmentSummaryPage appointmentSummaryPage;
    HistoryPage historyPage;

    String currentUrl;
    String expectedValidationMsg = "Please fill out this field.";

    @BeforeClass
    public void setup() {
        driver = BrowserUtils.createChromeDriver();
        action = new Action(driver);
        waitAction = new WaitAction(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        appointmentPage  = new AppointmentPage(driver);
        appointmentSummaryPage  = new AppointmentSummaryPage(driver);
        historyPage = new HistoryPage(driver);

        action.maximize();
        action.openWeb();
        homePage.clickMakeAppointment();
        loginPage.enterUsername(LoginTest.validUsername);
        loginPage.enterPassword(LoginTest.validPassword);
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        File screenShot = driver.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenShot, new File("./src/test/resources/screenshots/" + result.getName() + System.currentTimeMillis() + ".jpg"));
    }

    @AfterClass
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        action.quit();
    }

    @Test
    public void bookValid() {
        appointmentPage.selectFacility(appointmentPage.facilityTokyoFacilityOpt);
        appointmentPage.selectHospitalReadmission();
        appointmentPage.selectHealthcareProgram();
        appointmentPage.chooseDate();
        appointmentPage.enterComment();
        currentUrl = driver.getCurrentUrl();
        appointmentPage.clickBookAppointment();
        waitAction.waitUrlChanges(currentUrl);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/appointment.php#summary"));
        appointmentSummaryPage.clickGoToHomepage();
    }

    @Test (priority = 1)
    public void bookWithoutReadmission() {
        homePage.clickMakeAppointment();
        appointmentPage.selectFacility(appointmentPage.facilityHongkongFacilityOpt);
        appointmentPage.selectHealthcareProgram();
        appointmentPage.chooseDate();
        appointmentPage.enterComment();
        currentUrl = driver.getCurrentUrl();
        appointmentPage.clickBookAppointment();
        waitAction.waitUrlChanges(currentUrl);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/appointment.php#summary"));
        appointmentSummaryPage.clickGoToHomepage();
    }

    @Test (priority = 2)
    public void checkBookHistory() {
        appointmentPage.openMenu();
        currentUrl = driver.getCurrentUrl();
        appointmentPage.clickHistory();
        waitAction.waitUrlChanges(currentUrl);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/history.php#history"));
        historyPage.clickGoToHomepage();
    }

    @Test (priority = 3)
    public void bookEmptyDate() {
        homePage.clickMakeAppointment();
        appointmentPage.selectFacility(appointmentPage.facilitySeoulFacilityOpt);
        appointmentPage.selectHospitalReadmission();
        appointmentPage.selectHealthcareProgram();
        appointmentPage.enterComment();
        appointmentPage.clickBookAppointment();
        WebElement dateVisitField = driver.findElement(appointmentPage.dateVisit);
        Assert.assertEquals(action.getValidationMessage(dateVisitField), expectedValidationMsg);
    }
}
