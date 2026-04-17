package actionDriver;

import org.openqa.selenium.WebDriver;

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
}
