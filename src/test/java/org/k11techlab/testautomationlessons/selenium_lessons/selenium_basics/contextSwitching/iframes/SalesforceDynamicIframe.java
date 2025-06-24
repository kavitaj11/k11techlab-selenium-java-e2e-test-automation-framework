package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.contextSwitching.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SalesforceDynamicIframe {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://your-salesforce-instance.lightning.force.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the iframe is present in the DOM
        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));

        // Switch to the iframe
        driver.switchTo().frame(iframeElement);

        // Perform actions inside the iframe
        driver.findElement(By.id("some-element-inside-iframe")).click();

        // Switch back to the main page
        driver.switchTo().defaultContent();
        driver.quit();
    }
}
