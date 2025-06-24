package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.networkInteractionsCDP;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;

import java.util.List;
import java.util.Optional;

public class BlockResources {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools instance
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the Network domain
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block images and CSS files
        devTools.send(Network.setBlockedURLs(List.of("*.png", "*.jpg", "*.css", "*.gif")));
        System.out.println("Blocked images and CSS files");

        // Navigate to a webpage
        driver.get("https://example.com");

        driver.quit();
    }
}
