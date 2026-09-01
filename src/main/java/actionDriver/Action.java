package actionDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action {
    WebDriver driver;
    WebDriverWait wait;
    String webUrl = "https://katalon-demo-cura.herokuapp.com/";

    public Action(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForClickable(By locator, int waitDuration) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForVisibility(By locator, int waitDuration) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUrlChanges(String url, int waitDuration){
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void openWeb() {
        driver.get(webUrl);
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void quit() {
        driver.quit();
    }

    public String getValidationMessage(WebElement element)
    {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", element);
    }
}
