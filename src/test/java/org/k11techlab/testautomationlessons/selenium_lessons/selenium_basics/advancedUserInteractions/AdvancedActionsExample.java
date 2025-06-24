package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.advancedUserInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AdvancedActionsExample {

    public static void main(String[] args) {
        // Set up WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            // Create an instance of Actions class
            Actions actions = new Actions(driver);

            /** ================================
             * Advanced Mouse Actions Examples
             ================================= */

            // 1. Mouse Hover
            WebElement hoverElement = driver.findElement(By.id("hoverElementId"));
            actions.moveToElement(hoverElement).perform();
            System.out.println("Mouse hover performed");

            // 2. Right-Click (Context Click)
            WebElement rightClickElement = driver.findElement(By.id("rightClickElementId"));
            actions.contextClick(rightClickElement).perform();
            System.out.println("Right-click performed");

            // 3. Double-Click
            WebElement doubleClickElement = driver.findElement(By.id("doubleClickElementId"));
            actions.doubleClick(doubleClickElement).perform();
            System.out.println("Double-click performed");

            // 4. Drag and Drop
            WebElement sourceElement = driver.findElement(By.id("dragSourceId"));
            WebElement targetElement = driver.findElement(By.id("dropTargetId"));
            actions.dragAndDrop(sourceElement, targetElement).perform();
            System.out.println("Drag-and-drop performed");

            // 5. Click and Hold (Custom Drag and Drop)
            WebElement dragSource = driver.findElement(By.id("customDragSourceId"));
            WebElement dragTarget = driver.findElement(By.id("customDragTargetId"));
            actions.clickAndHold(dragSource)
                   .moveToElement(dragTarget)
                   .release()
                   .perform();
            System.out.println("Custom drag-and-drop performed");

            // 6. Mouse Click at an Offset
            WebElement offsetElement = driver.findElement(By.id("offsetElementId"));
            actions.moveToElement(offsetElement, 50, 30).click().perform(); // Click at offset (50,30) relative to the element
            System.out.println("Mouse click at an offset performed");

            /** ================================
             * Advanced Keyboard Actions Examples
             ================================= */

            // 7. Sending Keys to an Element
            WebElement inputElement = driver.findElement(By.id("inputFieldId"));
            actions.click(inputElement)
                   .sendKeys("Advanced Keyboard Actions")
                   .perform();
            System.out.println("Keys sent to element");

            // 8. Keyboard Key Combination (e.g., CTRL+A)
            actions.keyDown(inputElement, org.openqa.selenium.Keys.CONTROL)
                   .sendKeys("a")
                   .keyUp(org.openqa.selenium.Keys.CONTROL)
                   .perform();
            System.out.println("CTRL+A performed");

            // 9. Keyboard Interaction (Typing Special Keys)
            actions.sendKeys(org.openqa.selenium.Keys.ENTER).perform(); // Simulate pressing the Enter key
            System.out.println("Enter key pressed");

            /** ================================
             * Compound Actions Examples
             ================================= */

            // 10. Hover and Click (Compound Action)
            WebElement menu = driver.findElement(By.id("menuId"));
            WebElement subMenu = driver.findElement(By.id("subMenuId"));
            actions.moveToElement(menu)
                   .moveToElement(subMenu)
                   .click()
                   .perform();
            System.out.println("Hover and click performed");

            // 11. Keyboard and Mouse Combination
            WebElement elementForCombination = driver.findElement(By.id("combinationElementId"));
            actions.keyDown(org.openqa.selenium.Keys.SHIFT)
                   .click(elementForCombination)
                   .keyUp(org.openqa.selenium.Keys.SHIFT)
                   .perform();
            System.out.println("SHIFT + Click performed");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
