package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.contextSwitching;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class SalesforceWindowSwitch {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://your-salesforce-instance.lightning.force.com");

        // Login steps

        // Click on a link that opens in a new tab or window
        driver.findElement(By.linkText("Open in New Tab")).click();

        // Store the current window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Switch to the new window
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Perform actions in the new window
        System.out.println("New window title: " + driver.getTitle());

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        driver.quit();
    }
}

