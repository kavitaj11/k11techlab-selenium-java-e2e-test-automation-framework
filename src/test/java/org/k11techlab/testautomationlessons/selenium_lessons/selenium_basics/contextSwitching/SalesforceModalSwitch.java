package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.contextSwitching;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SalesforceModalSwitch {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://your-salesforce-instance.lightning.force.com");

        // Login steps

        // Click on a button that opens a modal
        driver.findElement(By.xpath("//button[text()='New']")).click();

        // Wait for the modal to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

        // Interact with elements inside the modal
        WebElement nameField = modal.findElement(By.xpath("//input[@name='Name']"));
        nameField.sendKeys("Test Record Name");

        // Click Save or Cancel
        modal.findElement(By.xpath("//button[text()='Save']")).click();

        driver.quit();
    }
}

