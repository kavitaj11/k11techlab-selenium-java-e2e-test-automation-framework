package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.shadowDomExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDomExample {
    public static void main(String[] args) {
        // Set up ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a page that contains Shadow DOM elements
            driver.get("https://your-salesforce-instance.lightning.force.com");

            // Example: Find a Shadow DOM host element (e.g., "lightning-input")
            WebElement shadowHost = driver.findElement(By.cssSelector("lightning-input"));

            // Use Selenium 4's getShadowRoot() to access the shadow root
            WebElement shadowRoot = (WebElement) shadowHost.getShadowRoot();

            // Find an element inside the Shadow DOM
            WebElement inputElement = shadowRoot.findElement(By.cssSelector("input"));

            // Interact with the element (e.g., enter text)
            inputElement.sendKeys("Test Input");

            System.out.println("Successfully interacted with Shadow DOM element!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
