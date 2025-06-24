package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.waitStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class SeleniumCustomWaitExample {

    public static void main(String[] args) {
        // Set up WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            /** ==========================
             * Custom Wait Implementation
             ========================== */

            // Create a FluentWait instance for custom conditions
            FluentWait<WebDriver> customWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))        // Maximum wait time
                    .pollingEvery(Duration.ofSeconds(2))        // Polling interval
                    .ignoring(NoSuchElementException.class);    // Ignore NoSuchElementException during polling

            // Define a custom condition to wait until an element contains specific text
            WebElement dynamicElement = customWait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.id("dynamicTextElement"));
                    String elementText = element.getText();
                    System.out.println("Current text: " + elementText);
                    if (elementText.contains("Loaded")) {
                        return element; // Return the element when the condition is met
                    }
                    return null; // Keep waiting
                }
            });

            // Interact with the element once the condition is met
            System.out.println("Dynamic element is ready: " + dynamicElement.getText());

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
