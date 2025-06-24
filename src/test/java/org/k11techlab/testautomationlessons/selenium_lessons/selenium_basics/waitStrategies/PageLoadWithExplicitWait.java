package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.waitStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class PageLoadWithExplicitWait {
    public static void main(String[] args) {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the webpage
            driver.get("https://example.com");

            // Wait for the document to be in "complete" state
            JavascriptExecutor js = (JavascriptExecutor) driver;
            waitForPageToLoad(js, 20);

            // Wait for a specific element to be visible (explicit wait)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicElementId")));
            System.out.println("Dynamic element is visible!");

        } finally {
            // Close the browser
            driver.quit();
        }
    }

    public static void waitForPageToLoad(JavascriptExecutor jsExecutor, int timeoutInSeconds) {
        new WebDriverWait((WebDriver) jsExecutor, Duration.ofSeconds(timeoutInSeconds))
                .until(driver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
        System.out.println("Page is fully loaded (document.readyState = complete)");
    }
}
