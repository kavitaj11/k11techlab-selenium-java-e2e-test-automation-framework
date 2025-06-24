package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.javascriptExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class HiddenElementWithJavaScript {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://example.com");

            // Locate the hidden element
            WebElement hiddenElement = driver.findElement(By.id("hiddenElementId"));

            // Use JavaScript to click on the hidden element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", hiddenElement);
            System.out.println("Hidden element clicked!");

            // Use JavaScript to set a value in a hidden input field
            js.executeScript("arguments[0].value='Text set via JavaScript';", hiddenElement);
            System.out.println("Text entered into hidden element!");

        } finally {
            driver.quit();
        }
    }
}
