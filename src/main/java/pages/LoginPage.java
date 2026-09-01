package pages;

import actionDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Action {
    By username = By.id("txt-username");
    By password = By.id("txt-password");
    By login = By.id("btn-login");
    By errorMsg = By.xpath("//p[@class='lead text-danger']");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
       super(driver);
       this.driver = driver;
    }

    public void enterUsername(String text) {
        waitForClickable(username, 15).sendKeys(text);
    }

    public void enterPassword(String text) {
        waitForClickable(password, 15).sendKeys(text);
    }

    public void clickLogin() {
        waitForClickable(login, 15).click();
    }

    public String assertErrorMsg() {
        return waitForVisibility(errorMsg, 25).getText();
    }
}
