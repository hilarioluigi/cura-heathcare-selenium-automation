package pages;

import actionDriver.WaitAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends WaitAction {
    By makeAppointment = By.id("btn-make-appointment");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMakeAppointment() {
        click(makeAppointment);
    }
}
