package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.networkInteractionsCDP;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.RequestId;

import java.util.Optional;

public class MockApiResponses {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools instance
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the Network domain
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Intercept and mock responses
        devTools.addListener(Network.requestIntercepted(), interceptedRequest -> {
            System.out.println("Intercepted URL: " + interceptedRequest.getRequest().getUrl());

            // Mock the response
            devTools.send(Network.continueInterceptedRequest(
                interceptedRequest.getInterceptionId(),
                Optional.empty(), // HTTP Error Code
                Optional.empty(), // Raw Response
                Optional.empty(), // Headers
                Optional.empty(), // Auth Challenge Response
                Optional.empty(),
                    Optional.empty(),
                    Optional.empty() // Binary data
            ));
        });

        driver.get("https://example.com");

        driver.quit();
    }
}
