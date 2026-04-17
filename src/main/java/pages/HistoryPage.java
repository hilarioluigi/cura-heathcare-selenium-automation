package pages;

import actionDriver.WaitAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HistoryPage extends WaitAction {
    By goToHomepageBtn = By.xpath("//a[@class='btn btn-default']");

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickGoToHomepage() {
        click(goToHomepageBtn);
    }
}
