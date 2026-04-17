package pages;

import actionDriver.WaitAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WaitAction {
    By username = By.xpath("//input[@name='username']");
    By password = By.xpath("//input[@name='password']");
    By login = By.id("btn-login");

    public LoginPage(WebDriver driver) {
       super(driver);
    }

    public void enterUsername(String text) {
        inputText(username, text);
    }

    public void enterPassword(String text) {
        inputText(password, text);
    }

    public void clickLogin() {
        click(login);
    }
}
