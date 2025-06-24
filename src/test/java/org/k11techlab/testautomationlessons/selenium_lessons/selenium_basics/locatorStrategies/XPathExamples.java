package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.locatorStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class XPathExamples {
    public static void main(String[] args) {
        // Set up the WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage (update with your test site)
            driver.get("https://example.com");

            /** =======================
             * Simple XPath Examples
             ======================== */

            // 1. Absolute XPath (Not Recommended)
            WebElement absoluteXPathElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/input"));
            absoluteXPathElement.sendKeys("Absolute XPath Example");
            System.out.println("Absolute XPath used");

            // 2. Basic XPath with Tag Name
            WebElement inputElement = driver.findElement(By.xpath("//input"));
            inputElement.sendKeys("Basic XPath Example");
            System.out.println("Basic XPath with tag name used");

            // 3. XPath with Attribute
            WebElement inputWithId = driver.findElement(By.xpath("//input[@id='search-box']"));
            inputWithId.sendKeys("XPath with specific attribute");

            // 4. XPath with Multiple Attributes
            WebElement inputWithMultipleAttributes = driver.findElement(By.xpath("//input[@type='text' and @placeholder='Search']"));
            inputWithMultipleAttributes.sendKeys("XPath with multiple attributes");

            /** ===========================
             * Moderate XPath Examples
             ============================ */

            // 5. XPath with Partial Attribute Match (contains())
            WebElement partialMatch = driver.findElement(By.xpath("//input[contains(@placeholder, 'ear')]"));
            partialMatch.sendKeys("XPath with contains()");

            // 6. XPath with Starts-With
            WebElement startsWithExample = driver.findElement(By.xpath("//button[starts-with(@id, 'btn-')]"));
            startsWithExample.click();

            // 7. XPath Based on Text Content
            WebElement textContentExample = driver.findElement(By.xpath("//a[text()='Click Here']"));
            textContentExample.click();

            // 8. XPath Based on Partial Text Content (contains())
            WebElement partialTextExample = driver.findElement(By.xpath("//a[contains(text(), 'Click')]"));
            partialTextExample.click();

            /** ===========================
             * Advanced XPath Examples
             ============================ */

            // 9. XPath with Parent-Child Relationship
            WebElement parentChildExample = driver.findElement(By.xpath("//div[@class='form-group']/input[@id='username']"));
            parentChildExample.sendKeys("Parent-Child XPath");

            // 10. XPath with Ancestor
            WebElement ancestorExample = driver.findElement(By.xpath("//input[@id='username']/ancestor::form"));
            System.out.println("Found ancestor: " + ancestorExample.getTagName());

            // 11. XPath with Following-Sibling
            WebElement followingSiblingExample = driver.findElement(By.xpath("//label[@for='password']/following-sibling::input"));
            followingSiblingExample.sendKeys("Following-Sibling Example");

            // 12. XPath with Preceding-Sibling
            WebElement precedingSiblingExample = driver.findElement(By.xpath("//input[@id='password']/preceding-sibling::label"));
            System.out.println("Preceding sibling text: " + precedingSiblingExample.getText());

            // 13. XPath with Descendant
            WebElement descendantExample = driver.findElement(By.xpath("//div[@class='container']//descendant::a[text()='Learn More']"));
            descendantExample.click();

            // 14. XPath to Find All Elements
            List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
            System.out.println("Total number of links: " + allLinks.size());

            // 15. XPath with Position
            WebElement firstItem = driver.findElement(By.xpath("(//ul[@id='items']/li)[1]"));
            System.out.println("First item text: " + firstItem.getText());

            WebElement lastItem = driver.findElement(By.xpath("(//ul[@id='items']/li)[last()]"));
            System.out.println("Last item text: " + lastItem.getText());

            // 16. XPath with Chained Contains
            WebElement chainedContainsExample = driver.findElement(By.xpath("//div[contains(@class, 'header')]/descendant::a[contains(text(), 'Home')]"));
            chainedContainsExample.click();

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
