package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.k11techlab.testautomationlessons.core_java_lessons.multithreading_examples.WebDriverProvider.getDriver;

/**
 * Created by Maor on 5/16/2018.
 */

public class SeleniumExceptionsExample {

    WebDriver driver;
    @Test
    // WebDriver is switching to an invalid/not available alert
    public void NoAlertPresentException(){
        try
        {
            driver= getDriver();
            driver.get("https://stackoverflow.com");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.switchTo().alert();
        }
        catch (NoAlertPresentException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    // WebDriver is unable to identify the element during run time
    // i.e. FindBy method can’t find the element
    public void NoSuchElementException() {

        try
        {
            driver= getDriver();
            driver.get("https://stackoverflow.com");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(By.id("display")).click();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void WebDriverException() {

        try
        {
            driver.manage().window().maximize();
        }
        catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Thrown when the driver is switching to an invalid frame
    public void NoSuchFrameException() {

        try
        {
            driver= getDriver();
            driver.get("https://stackoverflow.com");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.switchTo().frame("frame_a");
        }
        catch (NoSuchFrameException e)
        {
            e.printStackTrace();
        }
    }

        @Test
        // Thrown when the driver is switching to an invalid Window
        public void NoSuchWindowException() {

            try {
                driver= getDriver();
                driver.get("https://stackoverflow.com");
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.switchTo().window("invalidwindowname");
            } catch (NoSuchWindowException e) {
                e.printStackTrace();
            }
        }
    }