package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.contextSwitching.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class SalesforceMultipleIframes {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://your-salesforce-instance.lightning.force.com");

        // Find all iframes
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Total iframes found: " + iframes.size());

        // Iterate over all iframes and find the correct one
        for (WebElement iframe : iframes) {
            driver.switchTo().frame(iframe);

            // Check if desired element is present inside the iframe
            List<WebElement> elements = driver.findElements(By.id("report-table"));
            if (!elements.isEmpty()) {
                System.out.println("Found the correct iframe, performing action...");
                elements.get(0).click(); // Perform action
                break; // Stop iterating once the correct iframe is found
            }

            // Switch back to the main page before checking the next iframe
            driver.switchTo().defaultContent();
        }

        driver.quit();
    }
}
