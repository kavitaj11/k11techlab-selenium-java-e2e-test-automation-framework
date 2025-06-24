package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.waitStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class SeleniumWaitStrategiesExample {
    public static void main(String[] args) {
        // Set up WebDriver (make sure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            /** =======================
             * 1. Implicit Wait Example
             ======================== */
            // Set an implicit wait of 10 seconds
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Locate an element (Selenium will wait up to 10 seconds if the element is not immediately available)
            WebElement elementWithImplicitWait = driver.findElement(By.id("username"));
            elementWithImplicitWait.sendKeys("Implicit Wait Example");

            /** =======================
             * 2. Explicit Wait Example
             ======================== */
            // Create an explicit wait (waits up to 15 seconds for a specific condition)
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Wait until an element is visible on the page
            WebElement elementWithExplicitWait = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
            );
            elementWithExplicitWait.sendKeys("Explicit Wait Example");

            // Wait until a button is clickable
            WebElement clickableButton = explicitWait.until(
                ExpectedConditions.elementToBeClickable(By.id("loginButton"))
            );
            clickableButton.click();

            /** =======================
             * 3. Fluent Wait Example
             ======================== */
            // Create a FluentWait (custom wait with polling interval and exception handling)
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))       // Maximum wait time
                .pollingEvery(Duration.ofSeconds(2))       // Polling interval
                .ignoring(NoSuchElementException.class);   // Ignore NoSuchElementException during polling

            // Define a custom condition for the FluentWait
            WebElement elementWithFluentWait = fluentWait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.id("dynamicElement"));
                    if (element.isDisplayed()) {
                        return element;
                    } else {
                        return null; // Keep waiting
                    }
                }
            });

            // Interact with the dynamically loaded element
            elementWithFluentWait.sendKeys("Fluent Wait Example");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
