package pages;

import actionDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentSummaryPage extends Action {
    By goToHomepageBtn = By.cssSelector(".btn.btn-default");

    public AppointmentSummaryPage(WebDriver driver) {
        super(driver);
    }

    public void clickGoToHomepage() {
        waitForClickable(goToHomepageBtn, 15).click();
    }
}
