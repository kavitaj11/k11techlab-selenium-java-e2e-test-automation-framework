package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.locatorStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorStrategiesExample {
    public static void main(String[] args) {
        // Set up WebDriver (make sure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            /** =======================
             * Locator Strategies Examples
             ======================== */

            // 1. Locate by ID
            WebElement elementById = driver.findElement(By.id("search-box")); // Example: <input id="search-box" />
            elementById.sendKeys("Locating by ID");

            // 2. Locate by Name
            WebElement elementByName = driver.findElement(By.name("username")); // Example: <input name="username" />
            elementByName.sendKeys("Locating by Name");

            // 3. Locate by Class Name
            WebElement elementByClassName = driver.findElement(By.className("form-control")); // Example: <input class="form-control" />
            elementByClassName.sendKeys("Locating by Class Name");

            // 4. Locate by Tag Name
            WebElement elementByTagName = driver.findElement(By.tagName("button")); // Example: <button>Submit</button>
            elementByTagName.click();

            // 5. Locate by Link Text
            WebElement elementByLinkText = driver.findElement(By.linkText("Learn More")); // Example: <a href="/learn-more">Learn More</a>
            elementByLinkText.click();

            // 6. Locate by Partial Link Text
            WebElement elementByPartialLinkText = driver.findElement(By.partialLinkText("Learn")); // Partial match on "Learn More"
            elementByPartialLinkText.click();

            // 7. Locate by CSS Selector (Simple Selector)
            WebElement elementByCssSelectorSimple = driver.findElement(By.cssSelector("#search-box")); // Using ID
            elementByCssSelectorSimple.sendKeys("Locating by CSS Selector (Simple)");

            // 8. Locate by CSS Selector (Complex Selector)
            WebElement elementByCssSelectorComplex = driver.findElement(By.cssSelector("div.container > form input[name='password']")); // Child combinator
            elementByCssSelectorComplex.sendKeys("Locating by CSS Selector (Complex)");

            // 9. Locate by XPath (Simple XPath)
            WebElement elementByXPathSimple = driver.findElement(By.xpath("//input[@id='search-box']")); // Matches element by ID
            elementByXPathSimple.sendKeys("Locating by XPath (Simple)");

            // 10. Locate by XPath (Advanced XPath)
            WebElement elementByXPathComplex = driver.findElement(By.xpath("//div[@class='container']//input[@type='text']")); // Matches descendant elements
            elementByXPathComplex.sendKeys("Locating by XPath (Advanced)");

            // 11. Locate Multiple Elements
            List<WebElement> allLinks = driver.findElements(By.tagName("a")); // Finds all <a> elements
            System.out.println("Number of links on the page: " + allLinks.size());

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
