package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.locatorStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class CssSelectorExamples {
    public static void main(String[] args) {
        // Set up WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage (update with your test site)
            driver.get("https://example.com");

            /** =======================
             * Simple CSS Selector Examples
             ======================== */

            // 1. By Tag Name
            WebElement tagNameExample = driver.findElement(By.cssSelector("input"));
            tagNameExample.sendKeys("Tag Name Example");

            // 2. By ID
            WebElement idExample = driver.findElement(By.cssSelector("#search-box")); // ID selector
            idExample.sendKeys("ID Example");

            // 3. By Class
            WebElement classExample = driver.findElement(By.cssSelector(".form-control")); // Class selector
            classExample.sendKeys("Class Example");

            // 4. By Attribute
            WebElement attributeExample = driver.findElement(By.cssSelector("input[type='text']")); // Attribute selector
            attributeExample.sendKeys("Attribute Example");

            // 5. By Multiple Attributes
            WebElement multipleAttributesExample = driver.findElement(By.cssSelector("input[type='text'][placeholder='Search']"));
            multipleAttributesExample.sendKeys("Multiple Attributes Example");

            /** ===========================
             * Moderate CSS Selector Examples
             ============================ */

            // 6. Partial Match with Attribute (substring matches)
            WebElement containsExample = driver.findElement(By.cssSelector("input[placeholder*='ear']")); // Contains substring
            containsExample.sendKeys("Partial Match Example (contains)");

            WebElement startsWithExample = driver.findElement(By.cssSelector("input[placeholder^='Sea']")); // Starts with
            startsWithExample.sendKeys("Partial Match Example (starts with)");

            WebElement endsWithExample = driver.findElement(By.cssSelector("input[placeholder$='rch']")); // Ends with
            endsWithExample.sendKeys("Partial Match Example (ends with)");

            // 7. Direct Child Selector
            WebElement directChildExample = driver.findElement(By.cssSelector("div > input")); // Finds direct child input under div
            directChildExample.sendKeys("Direct Child Example");

            // 8. Descendant Selector
            WebElement descendantExample = driver.findElement(By.cssSelector("div.container input[type='text']")); // Finds descendant input inside div with class 'container'
            descendantExample.sendKeys("Descendant Example");

            // 9. Combine Class and Attribute
            WebElement classAndAttributeExample = driver.findElement(By.cssSelector("input.form-control[placeholder='Search']"));
            classAndAttributeExample.sendKeys("Class and Attribute Example");

            /** ===========================
             * Advanced CSS Selector Examples
             ============================ */

            // 10. First-of-Type Selector
            WebElement firstOfTypeExample = driver.findElement(By.cssSelector("ul#items li:first-of-type")); // First <li> in <ul> with ID 'items'
            System.out.println("First Item Text: " + firstOfTypeExample.getText());

            // 11. Last-of-Type Selector
            WebElement lastOfTypeExample = driver.findElement(By.cssSelector("ul#items li:last-of-type")); // Last <li> in <ul> with ID 'items'
            System.out.println("Last Item Text: " + lastOfTypeExample.getText());

            // 12. nth-of-Type Selector
            WebElement nthOfTypeExample = driver.findElement(By.cssSelector("ul#items li:nth-of-type(2)")); // Second <li> in <ul> with ID 'items'
            System.out.println("Second Item Text: " + nthOfTypeExample.getText());

            // 13. Sibling Selectors
            WebElement adjacentSiblingExample = driver.findElement(By.cssSelector("label + input")); // Finds <input> immediately after <label>
            adjacentSiblingExample.sendKeys("Adjacent Sibling Example");

            WebElement generalSiblingExample = driver.findElement(By.cssSelector("label ~ input")); // Finds all <input> siblings after <label>
            generalSiblingExample.sendKeys("General Sibling Example");

            // 14. Grouping Selectors
            List<WebElement> groupedElements = driver.findElements(By.cssSelector("input, button")); // Finds all <input> and <button> elements
            System.out.println("Number of input and button elements: " + groupedElements.size());

            // 15. Complex Chained Selectors
            WebElement chainedExample = driver.findElement(By.cssSelector("div.container > form#login-form input[name='username']"));
            chainedExample.sendKeys("Complex Chained Selector Example");

            // 16. Negation Selector
            WebElement negationExample = driver.findElement(By.cssSelector("input:not([type='password'])")); // Finds an input that's not a password field
            negationExample.sendKeys("Negation Selector Example");

            /** ===========================
             * Additional Useful Scenarios
             ============================ */

            // Find all links in the footer
            List<WebElement> footerLinks = driver.findElements(By.cssSelector("footer a"));
            System.out.println("Number of links in footer: " + footerLinks.size());

            // Select an element based on multiple classes
            WebElement multiClassExample = driver.findElement(By.cssSelector(".btn.primary-action")); // Element with both 'btn' and 'primary-action' classes
            multiClassExample.click();

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
