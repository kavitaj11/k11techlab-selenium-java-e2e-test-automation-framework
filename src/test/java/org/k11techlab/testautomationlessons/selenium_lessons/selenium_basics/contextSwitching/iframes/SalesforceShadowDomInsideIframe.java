package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics.contextSwitching.iframes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class SalesforceShadowDomInsideIframe {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://your-salesforce-instance.lightning.force.com");

        // Switch to iframe first
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        // Access shadow root inside the iframe using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowHost = driver.findElement(By.cssSelector("lightning-input"));
        WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
        
        // Find and interact with an element inside the shadow DOM
        WebElement inputField = shadowRoot.findElement(By.cssSelector("input"));
        inputField.sendKeys("Test Input");

        driver.quit();
    }
}
