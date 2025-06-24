package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumBasicCommands {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public SeleniumBasicCommands() {
        // Set up WebDriver (Ensure you have the correct WebDriver binary)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();

        // Initialize WebDriverWait and Actions
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    /** ================================
     * Navigation Commands
     ================================ */

    public void openUrl(String url) {
        driver.get(url);
        System.out.println("Opened URL: " + url);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
        System.out.println("Navigated to: " + url);
    }

    public void navigateBack() {
        driver.navigate().back();
        System.out.println("Navigated back to the previous page");
    }

    public void navigateForward() {
        driver.navigate().forward();
        System.out.println("Navigated forward to the next page");
    }

    public void refreshPage() {
        driver.navigate().refresh();
        System.out.println("Page refreshed");
    }

    /** ================================
     * Element Interaction Commands
     ================================ */

    // Find and click an element
    public void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        System.out.println("Clicked on element: " + locator);
    }

    // Enter text into an input field
    public void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        System.out.println("Entered text '" + text + "' into element: " + locator);
    }

    // Get text from an element
    public String getElementText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String text = element.getText();
        System.out.println("Text retrieved from element: " + text);
        return text;
    }

    // Select an option from a dropdown by visible text
    public void selectDropdownByText(By locator, String visibleText) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Selected '" + visibleText + "' from dropdown: " + locator);
    }

    // Select an option from a dropdown by index
    public void selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        System.out.println("Selected option at index " + index + " from dropdown: " + locator);
    }

    // Check if an element is displayed
    public boolean isElementDisplayed(By locator) {
        boolean isDisplayed = driver.findElement(locator).isDisplayed();
        System.out.println("Element " + locator + " is displayed: " + isDisplayed);
        return isDisplayed;
    }

    /** ================================
     * Window and Frame Handling Commands
     ================================ */

    // Get the current window handle
    public String getCurrentWindowHandle() {
        String windowHandle = driver.getWindowHandle();
        System.out.println("Current window handle: " + windowHandle);
        return windowHandle;
    }

    // Switch to a specific window by handle
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
        System.out.println("Switched to window: " + windowHandle);
    }

    // Switch to a frame by locator
    public void switchToFrame(By locator) {
        WebElement frame = driver.findElement(locator);
        driver.switchTo().frame(frame);
        System.out.println("Switched to frame: " + locator);
    }

    // Switch back to the default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        System.out.println("Switched back to default content");
    }

    /** ================================
     * Alert Handling Commands
     ================================ */

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("Accepted the alert");
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
        System.out.println("Dismissed the alert");
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text: " + alertText);
        return alertText;
    }

    /** ================================
     * Utility Commands
     ================================ */

    // Get the current page title
    public String getPageTitle() {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        return title;
    }

    // Get the current URL
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        return url;
    }

    // Get all cookies
    public void printAllCookies() {
        List<Cookie> cookies = new ArrayList<>(driver.manage().getCookies());
        System.out.println("Cookies on the page: ");
        cookies.forEach(cookie -> System.out.println(cookie.getName() + ": " + cookie.getValue()));
    }

    // Take a screenshot
    public void takeScreenshot(String filePath) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(filePath);
        try {
            org.apache.commons.io.FileUtils.copyFile(screenshotFile, destinationFile);
            System.out.println("Screenshot saved to: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** ================================
     * Cleanup Commands
     ================================ */

    // Close the browser
    public void closeBrowser() {
        driver.quit();
        System.out.println("Browser closed");
    }

    /** ================================
     * Main Method (Test the Commands)
     ================================ */

    public static void main(String[] args) {
        SeleniumBasicCommands selenium = new SeleniumBasicCommands();

        try {
            // Example usage
            selenium.openUrl("https://example.com");

            // Get page title and URL
            selenium.getPageTitle();
            selenium.getCurrentUrl();

            // Interact with elements
            selenium.enterText(By.id("searchInput"), "Selenium");
            selenium.clickElement(By.id("searchButton"));

            // Handle dropdowns
            selenium.selectDropdownByText(By.id("dropdown"), "Option 1");

            // Handle alerts
            selenium.acceptAlert();

            // Take a screenshot
            selenium.takeScreenshot("screenshot.png");

        } finally {
            selenium.closeBrowser();
        }
    }
}
