package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.javascriptExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class JavaScriptExecutorExample {
    public static void main(String[] args) {
        // Set up WebDriver (ensure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to a sample webpage
            driver.get("https://example.com");

            // Create an instance of JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            /** ================================
             * Common JavaScriptExecutor Scenarios
             ================================ */

            // 1. Scroll Down to the Bottom of the Page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            System.out.println("Scrolled to the bottom of the page");

            // 2. Scroll to a Specific Element
            WebElement scrollToElement = driver.findElement(By.id("footer"));
            js.executeScript("arguments[0].scrollIntoView(true);", scrollToElement);
            System.out.println("Scrolled to the element");

            // 3. Click an Element (Bypassing Selenium's Click)
            WebElement button = driver.findElement(By.id("hiddenButton"));
            js.executeScript("arguments[0].click();", button);
            System.out.println("Clicked the hidden button");

            // 4. Enter Text in a Hidden or Disabled Input Field
            WebElement inputField = driver.findElement(By.id("disabledInput"));
            js.executeScript("arguments[0].value='Text via JavaScript';", inputField);
            System.out.println("Text entered in disabled input field");

            // 5. Highlight an Element
            WebElement elementToHighlight = driver.findElement(By.id("highlightElement"));
            js.executeScript("arguments[0].style.border='3px solid red'", elementToHighlight);
            System.out.println("Highlighted the element");

            // 6. Retrieve the Page Title
            String pageTitle = js.executeScript("return document.title;").toString();
            System.out.println("Page title is: " + pageTitle);

            // 7. Retrieve the Inner Text of an Element
            WebElement textElement = driver.findElement(By.id("textElement"));
            String innerText = js.executeScript("return arguments[0].innerText;", textElement).toString();
            System.out.println("Inner text of the element: " + innerText);

            // 8. Trigger an Alert
            js.executeScript("alert('This is a test alert');");
            System.out.println("Alert triggered");

            // 9. Refresh the Page
            js.executeScript("history.go(0);");
            System.out.println("Page refreshed");

            // 10. Disable a Button (Manipulate DOM)
            WebElement buttonToDisable = driver.findElement(By.id("buttonId"));
            js.executeScript("arguments[0].setAttribute('disabled', 'true');", buttonToDisable);
            System.out.println("Disabled the button");

            // 11. Retrieve the Browser URL
            String currentURL = js.executeScript("return document.URL;").toString();
            System.out.println("Current URL: " + currentURL);

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
