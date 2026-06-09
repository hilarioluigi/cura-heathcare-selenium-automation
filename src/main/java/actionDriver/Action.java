package actionDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Action {
    WebDriver driver;
    String webUrl = "https://katalon-demo-cura.herokuapp.com/";

    public Action(WebDriver driver) {
        this.driver = driver;
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
