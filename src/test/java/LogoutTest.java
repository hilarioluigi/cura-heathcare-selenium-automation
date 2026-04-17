import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.AppointmentPage;
import utils.BrowserUtils;

public class LogoutTest {
    ChromeDriver driver = BrowserUtils.createChromeDriver();
    AppointmentPage appointmentPage = new AppointmentPage(driver);

    @Test
    public void logout() {
        appointmentPage.openMenu();
        appointmentPage.clickLogout();
    }
}
