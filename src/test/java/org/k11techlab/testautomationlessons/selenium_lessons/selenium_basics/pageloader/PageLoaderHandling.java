package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.pageloader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.k11techlab.testautomationlessons.core_java_lessons.multithreading_examples.WebDriverProvider.getDriver;

public class PageLoaderHandling {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        // Set up WebDriver
         try {
             driver= getDriver();
            // Navigate to the target page
            driver.get("https://salesforce.com");

            // Wait for the loader to disappear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader"))); // Replace with actual loader selector

            System.out.println("Loader disappeared, proceeding...");

            // Wait for a specific element to be present
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("target-element"))); // Replace with actual selector
            System.out.println("Page and target element are ready.");

            // Perform actions after loader disappears
            element.click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

