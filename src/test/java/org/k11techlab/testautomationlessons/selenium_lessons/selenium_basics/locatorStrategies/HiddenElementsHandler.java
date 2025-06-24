package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.locatorStrategies;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HiddenElementsHandler {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private Actions actions;

    public HiddenElementsHandler() {
        // Set up WebDriver (Make sure you have the appropriate WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    /** =======================
     * Methods for Handling Hidden Elements
     ======================= */

    // 1. Scroll to an Element
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("Scrolled to element: " + locator);
    }

    // 2. Click on a Hidden Element
    public void clickHiddenElement(By locator) {
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].click();", element);
        System.out.println("Clicked on hidden element: " + locator);
    }

    // 3. Enter Text into a Hidden Input Field
    public void enterTextInHiddenField(By locator, String text) {
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].value='" + text + "';", element);
        System.out.println("Entered text into hidden field: " + locator);
    }

    // 4. Highlight an Element
    public void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].style.border='3px solid red';", element);
        System.out.println("Highlighted element: " + locator);
    }

    // 5. Explicit Wait for Element Visibility
    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        System.out.println("Element is visible: " + locator);
        return element;
    }

    // 6. Handle Hidden Checkbox
    public void checkHiddenCheckbox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].checked=true;", checkbox);
        System.out.println("Checked hidden checkbox: " + locator);
    }

    // 7. Handle Hidden File Upload
    public void uploadFileToHiddenInput(By locator, String filePath) {
        WebElement fileInput = driver.findElement(locator);
        fileInput.sendKeys(filePath);
        System.out.println("File uploaded to hidden input: " + locator);
    }

    // 8. Handle Hidden Dropdown Option
    public void selectHiddenDropdownOption(By locator) {
        WebElement option = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].selected=true;", option);
        System.out.println("Selected hidden dropdown option: " + locator);
    }

    // 9. Modify Element Visibility
    public void makeElementVisible(By locator) {
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript("arguments[0].style.display='block';", element);
        System.out.println("Made element visible: " + locator);
    }

    // 10. Hover Over an Element
    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        System.out.println("Hovered over element: " + locator);
    }

    // 11. Trigger a Custom Event (e.g., Mouse Over)
    public void triggerMouseOverEvent(By locator) {
        WebElement element = driver.findElement(locator);
        jsExecutor.executeScript(
            "var event = new MouseEvent('mouseover', { bubbles: true, cancelable: true });" +
            "arguments[0].dispatchEvent(event);",
            element
        );
        System.out.println("Mouse over event triggered on element: " + locator);
    }

    // 12. Wait for Page to Load Completely
    public void waitForPageToLoad(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(driver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
        System.out.println("Page fully loaded (document.readyState = complete)");
    }

    /** =======================
     * Utility Methods
     ======================= */

    // Close the browser
    public void closeBrowser() {
        driver.quit();
    }

    // Get the WebDriver instance (if needed)
    public WebDriver getDriver() {
        return driver;
    }

    // Example of usage for debugging
    public void printCurrentUrl() {
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    /** =======================
     * Main Method for Testing
     ======================= */

    public static void main(String[] args) {
        HiddenElementsHandler handler = new HiddenElementsHandler();

        try {
            // Open the test page
            handler.openUrl("https://example.com");

            // Example 1: Wait for page to load
            handler.waitForPageToLoad(15);

            // Example 2: Scroll to a hidden element
            handler.scrollToElement(By.id("footer"));

            // Example 3: Click on a hidden element
            handler.clickHiddenElement(By.id("hiddenButton"));

            // Example 4: Enter text into a hidden input field
            handler.enterTextInHiddenField(By.id("hiddenInput"), "Test Input");

            // Example 5: Highlight an element
            handler.highlightElement(By.id("header"));

            // Example 6: Handle a hidden checkbox
            handler.checkHiddenCheckbox(By.id("hiddenCheckbox"));

            // Example 7: Handle a hidden file upload
            handler.uploadFileToHiddenInput(By.id("hiddenFileInput"), "C:\\path\\to\\file.txt");

            // Example 8: Handle hidden dropdown option
            handler.selectHiddenDropdownOption(By.xpath("//option[@value='hiddenOption']"));

            // Example 9: Hover over an element
            handler.hoverOverElement(By.id("hoverElement"));

            // Example 10: Trigger a mouseover event
            handler.triggerMouseOverEvent(By.id("menuItem"));

            // Debugging utility
            handler.printCurrentUrl();

        } finally {
            // Close the browser
            handler.closeBrowser();
        }
    }
}
