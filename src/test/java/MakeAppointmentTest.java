import actionDriver.Action;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
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
        appointmentPage.clickBookAppointment();
        appointmentSummaryPage.clickGoToHomepage();
    }

    @Test (priority = 1)
    public void bookWithoutReadmission() {
        homePage.clickMakeAppointment();
        appointmentPage.selectFacility(appointmentPage.facilityHongkongFacilityOpt);
        appointmentPage.selectHealthcareProgram();
        appointmentPage.chooseDate();
        appointmentPage.enterComment();
        appointmentPage.clickBookAppointment();
        appointmentSummaryPage.clickGoToHomepage();
    }

    @Test (priority = 2)
    public void checkBookHistory() {
        appointmentPage.openMenu();
        appointmentPage.clickHistory();
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
    }
}
