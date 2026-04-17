package pages;

import actionDriver.WaitAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentSummaryPage extends WaitAction {
    By goToHomepageBtn = By.xpath("//a[@class='btn btn-default']");

    public AppointmentSummaryPage(WebDriver driver) {
        super(driver);
    }

    public void clickGoToHomepage() {
        click(goToHomepageBtn);
    }
}
