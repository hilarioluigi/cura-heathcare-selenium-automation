package actionDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Element;
import java.time.Duration;

public class WaitAction {
    WebDriver driver;
    WebDriverWait wait;

    public WaitAction(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void inputText(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void waitUrlChanges(String url){
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }
}
