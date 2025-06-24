package org.k11techlab.testautomationlessons.selenium_lessons.selenium4_latest_features.shadowDomExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class javascriptShadowDomSwitch {
    public static void main(String[] args) {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open Salesforce login page
            driver.get("https://your-salesforce-instance.lightning.force.com");
            driver.manage().window().maximize();

            // Step 2: Log in to Salesforce
            driver.findElement(By.id("username")).sendKeys("your-username");
            driver.findElement(By.id("password")).sendKeys("your-password");
            driver.findElement(By.id("Login")).click();

            // Wait for Lightning App to load (use WebDriverWait in real scenarios)
            Thread.sleep(10000); // Replace with explicit wait in production code

            // Step 3: Access Shadow DOM in Lightning Web Component
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Find the Shadow Host (e.g., a lightning-input component)
            WebElement shadowHost = driver.findElement(By.cssSelector("lightning-input"));

            // Get the Shadow Root
            WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);

            // Interact with an input element inside the Shadow DOM
            WebElement inputField = shadowRoot.findElement(By.cssSelector("input"));
            inputField.sendKeys("Test Input");

            System.out.println("Successfully interacted with Shadow DOM element!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 4: Close the browser
            driver.quit();
        }
    }
}
