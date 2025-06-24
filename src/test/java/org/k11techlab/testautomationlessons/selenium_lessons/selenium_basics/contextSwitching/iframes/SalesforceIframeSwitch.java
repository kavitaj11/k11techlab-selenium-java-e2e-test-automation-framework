package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.contextSwitching.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SalesforceIframeSwitch {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://your-salesforce-instance.lightning.force.com");

        // Login steps (if applicable)

        // Locate and switch to the iframe
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        // Perform actions inside the iframe
        WebElement inputField = driver.findElement(By.xpath("//input[@placeholder='Search Salesforce']"));
        inputField.sendKeys("Test Query");

        // Switch back to the main content
        driver.switchTo().defaultContent();

        driver.quit();
    }
}

