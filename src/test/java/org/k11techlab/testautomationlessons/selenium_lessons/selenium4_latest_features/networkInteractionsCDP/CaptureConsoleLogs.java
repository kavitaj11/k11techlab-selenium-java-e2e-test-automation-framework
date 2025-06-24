package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.networkInteractionsCDP;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;

public class CaptureConsoleLogs {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools instance
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the Log domain
        devTools.send(Log.enable());

        // Add listener for console logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("Log Level: " + logEntry.getLevel());
            System.out.println("Log Text: " + logEntry.getText());
            System.out.println("Source: " + logEntry.getSource());
        });

        driver.get("https://example.com");

        driver.quit();
    }
}
