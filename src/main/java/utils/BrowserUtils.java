package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class BrowserUtils {
    public static ChromeDriver createChromeDriver(){
        ChromeOptions options = new ChromeOptions();

        Map<String, Object>prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }
}
