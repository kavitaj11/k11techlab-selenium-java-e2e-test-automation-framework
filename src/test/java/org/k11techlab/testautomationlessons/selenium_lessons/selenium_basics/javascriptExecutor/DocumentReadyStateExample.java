package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.javascriptExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DocumentReadyStateExample {
    public static void main(String[] args) {
        // Set up WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            // Wait until the document is fully loaded using JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            boolean pageLoaded = waitForPageToLoad(js, 20); // 20 seconds timeout
            if (pageLoaded) {
                System.out.println("Page fully loaded!");
            } else {
                System.out.println("Page did not load within the timeout period.");
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }

    /**
     * Waits for the page to reach the "complete" readyState.
     *
     * @param jsExecutor The JavaScriptExecutor instance.
     * @param timeoutInSeconds The maximum time to wait for the page to load.
     * @return True if the page loaded completely within the timeout, false otherwise.
     */
    public static boolean waitForPageToLoad(JavascriptExecutor jsExecutor, int timeoutInSeconds) {
        int elapsed = 0;
        int pollInterval = 500; // Poll every 500ms

        while (elapsed < timeoutInSeconds * 1000) {
            // Check the document.readyState
            String readyState = jsExecutor.executeScript("return document.readyState").toString();
            System.out.println("Current readyState: " + readyState);

            if ("complete".equals(readyState)) {
                return true; // Page is fully loaded
            }

            try {
                // Wait before polling again
                Thread.sleep(pollInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            elapsed += pollInterval; // Increment elapsed time
        }

        return false; // Page did not load within the timeout
    }
}
