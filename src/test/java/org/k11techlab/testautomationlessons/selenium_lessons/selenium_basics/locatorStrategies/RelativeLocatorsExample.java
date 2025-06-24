package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.locatorStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocatorsExample {

    public static void main(String[] args) {
        // Set up WebDriver (Ensure you have the correct WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");

            // Example of Relative Locators

            /** 1. Locate an Element Using `above()` */
            WebElement referenceElement = driver.findElement(By.xpath("//h2[text()='Relative Locators']"));
            WebElement elementAbove = driver.findElement(RelativeLocator.with(By.tagName("h1")).above(referenceElement));
            System.out.println("Element above: " + elementAbove.getText());

            /** 2. Locate an Element Using `below()` */
            WebElement elementBelow = driver.findElement(RelativeLocator.with(By.tagName("p")).below(referenceElement));
            System.out.println("Element below: " + elementBelow.getText());

            /** 3. Locate an Element Using `toLeftOf()` */
            WebElement referenceRightElement = driver.findElement(By.xpath("//h3[text()='Accessibility']"));
            WebElement elementToLeft = driver.findElement(RelativeLocator.with(By.tagName("img")).toLeftOf(referenceRightElement));
            System.out.println("Element to the left: " + elementToLeft.getAttribute("alt"));

            /** 4. Locate an Element Using `toRightOf()` */
            WebElement referenceLeftElement = driver.findElement(By.xpath("//h3[text()='BrowserStack']"));
            WebElement elementToRight = driver.findElement(RelativeLocator.with(By.tagName("img")).toRightOf(referenceLeftElement));
            System.out.println("Element to the right: " + elementToRight.getAttribute("alt"));

            /** 5. Locate an Element Using `near()` */
            WebElement elementNear = driver.findElement(RelativeLocator.with(By.tagName("a")).near(referenceElement, 100)); // Within 100px
            System.out.println("Element near: " + elementNear.getText());

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
