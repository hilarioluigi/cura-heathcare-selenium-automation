package pages;

import actionDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Action {
    By makeAppointment = By.id("btn-make-appointment");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMakeAppointment() {
        waitForClickable(makeAppointment, 15).click();
    }
}
