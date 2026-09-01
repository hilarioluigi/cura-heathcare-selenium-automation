package test;

import actionDriver.Action;
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

    HomePage homePage;
    LoginPage loginPage;
    AppointmentPage appointmentPage;
    AppointmentSummaryPage appointmentSummaryPage;
    HistoryPage historyPage;

    String currentUrl;
    String expectedValidationMsg = "Please fill out this field.";

    String facilityTokyoFacilityOpt = "Tokyo CURA Healthcare Center";
    String facilityHongkongFacilityOpt = "Hongkong CURA Healthcare Center";
    String facilitySeoulFacilityOpt = "Seoul CURA Healthcare Center";

    @BeforeClass
    public void setup() {
        driver = BrowserUtils.createChromeDriver();
        action = new Action(driver);
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
        FileHandler.copy(screenShot, new File("./src/test/java/resources/screenshots/" + result.getName() + System.currentTimeMillis() + ".jpg"));
    }

    @AfterClass
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        action.quit();
    }

    @Test
    public void bookValid(){
        appointmentPage.selectFacility(facilityTokyoFacilityOpt);
        appointmentPage.selectHospitalReadmission();
        appointmentPage.selectHealthcareProgram();
        appointmentPage.clickVisitDate();
        appointmentPage.chooseDate();
        appointmentPage.enterComment();
        currentUrl = driver.getCurrentUrl();
        appointmentPage.clickBookAppointment();
        action.waitUrlChanges(currentUrl, 15);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/appointment.php#summary"));
        appointmentSummaryPage.clickGoToHomepage();
    }

    @Test (priority = 1)
    public void bookWithoutReadmission(){
        homePage.clickMakeAppointment();
        appointmentPage.selectFacility(facilityHongkongFacilityOpt);
        appointmentPage.selectHealthcareProgram();
        appointmentPage.clickVisitDate();
        appointmentPage.chooseDate();
        appointmentPage.enterComment();
        currentUrl = driver.getCurrentUrl();
        appointmentPage.clickBookAppointment();
        action.waitUrlChanges(currentUrl, 15);
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
        action.waitUrlChanges(currentUrl, 15);
        currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("/history.php#history"));
        historyPage.clickGoToHomepage();
    }

    @Test (priority = 3)
    public void bookEmptyDate() {
        homePage.clickMakeAppointment();
        appointmentPage.selectFacility(facilitySeoulFacilityOpt);
        appointmentPage.selectHospitalReadmission();
        appointmentPage.selectHealthcareProgram();
        appointmentPage.enterComment();
        appointmentPage.clickBookAppointment();
        WebElement dateVisitField = driver.findElement(appointmentPage.dateVisit);
        Assert.assertEquals(action.getValidationMessage(dateVisitField), expectedValidationMsg);
    }
}
