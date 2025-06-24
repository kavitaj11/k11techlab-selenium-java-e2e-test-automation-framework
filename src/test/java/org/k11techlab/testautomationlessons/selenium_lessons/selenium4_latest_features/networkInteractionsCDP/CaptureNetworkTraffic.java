package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.networkInteractionsCDP;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;

import java.util.Optional;

public class CaptureNetworkTraffic {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools instance
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the Network domain
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Add a listener to capture network traffic
        devTools.addListener(Network.requestWillBeSent(), request -> {
            System.out.println("Request URL: " + request.getRequest().getUrl());
            System.out.println("Request Method: " + request.getRequest().getMethod());
            System.out.println("Request Headers: " + request.getRequest().getHeaders());
        });

        // Add a listener to capture responses
        devTools.addListener(Network.responseReceived(), response -> {
            System.out.println("Response URL: " + response.getResponse().getUrl());
            System.out.println("Response Status: " + response.getResponse().getStatus());
            System.out.println("Response Headers: " + response.getResponse().getHeaders());
        });

        // Navigate to a webpage
        driver.get("https://example.com");

        driver.quit();
    }
}
