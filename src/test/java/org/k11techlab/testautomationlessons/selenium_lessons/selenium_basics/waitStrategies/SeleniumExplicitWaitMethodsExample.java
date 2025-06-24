package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.waitStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumExplicitWaitMethodsExample {
    public static void main(String[] args) {
        // Set up WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            // Initialize WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            /** =====================
             * Explicit Wait Methods
             ===================== */

            // 1. Wait for Element to be Visible
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            visibleElement.sendKeys("TestUser");

            // 2. Wait for Element to be Clickable
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
            clickableElement.click();

            // 3. Wait for Presence of Element in DOM
            WebElement presentElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            presentElement.sendKeys("TestPassword");

            // 4. Wait for Text to be Present in Element
            WebElement textElement = driver.findElement(By.id("statusMessage"));
            wait.until(ExpectedConditions.textToBePresentInElement(textElement, "Login Successful"));

            // 5. Wait for Attribute Value of an Element
            WebElement attributeElement = driver.findElement(By.id("dynamicButton"));
            wait.until(ExpectedConditions.attributeToBe(attributeElement, "disabled", "false"));

            // 6. Wait for Element to be Selected (e.g., checkbox or radio button)
            WebElement checkbox = driver.findElement(By.id("termsCheckbox"));
            wait.until(ExpectedConditions.elementToBeSelected(checkbox));

            // 7. Wait for Alert to be Present
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            // 8. Wait for Number of Elements to be More Than a Specific Count
            List<WebElement> listItems = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".menu-item"), 3));
            System.out.println("Number of menu items: " + listItems.size());

            // 9. Wait for Element's Text to Change
            WebElement changingTextElement = driver.findElement(By.id("dynamicText"));
            wait.until(ExpectedConditions.textToBe(By.id("dynamicText"), "Updated Text"));

            // 10. Wait for URL to Contain a Specific String
            wait.until(ExpectedConditions.urlContains("dashboard"));

            // 11. Wait for URL to Be Exactly Equal to a Specific String
            wait.until(ExpectedConditions.urlToBe("https://example.com/dashboard"));

            // 12. Wait for Title to Contain a Specific String
            wait.until(ExpectedConditions.titleContains("Welcome"));

            // 13. Wait for Title to Be Exactly Equal to a Specific String
            wait.until(ExpectedConditions.titleIs("Welcome to Example"));

            // 14. Wait for Frame and Switch to It
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("frame1")));

            System.out.println("All explicit wait examples executed successfully!");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
