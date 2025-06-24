package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.networkInteractionsCDP;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.*;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;

import java.util.Optional;

public class SimulateNetworkThrottling {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools instance
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the Network domain
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Set up network conditions to simulate 3G
        devTools.send(Network.emulateNetworkConditions(
                false,       // Set to false to simulate online mode
                100,         // Latency in milliseconds
                50000,       // Download throughput in bytes per second (50 KB/s)
                20000,       // Upload throughput in bytes per second (20 KB/s)
                Optional.of(ConnectionType.WIFI)// Simulate a 3G network
        ));
        System.out.println("Simulated 3G network conditions");

        driver.get("https://example.com");

        driver.quit();
    }
}
