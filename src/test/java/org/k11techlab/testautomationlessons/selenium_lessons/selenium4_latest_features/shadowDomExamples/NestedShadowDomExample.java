package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.shadowDomExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedShadowDomExample {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the page
            driver.get("https://example-with-nested-shadow-dom.com");

            // Step 1: Find the outer shadow host
            WebElement outerHost = driver.findElement(By.cssSelector("outer-shadow-host"));
            WebElement outerRoot = (WebElement) outerHost.getShadowRoot();

            // Step 2: Find the inner shadow host within the outer Shadow DOM
            WebElement innerHost = outerRoot.findElement(By.cssSelector("inner-shadow-host"));
            WebElement innerRoot = (WebElement) innerHost.getShadowRoot();

            // Step 3: Interact with an element inside the inner Shadow DOM
            WebElement targetElement = innerRoot.findElement(By.cssSelector("input"));
            targetElement.sendKeys("Nested Shadow DOM Input");

            System.out.println("Successfully interacted with nested Shadow DOM element!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
