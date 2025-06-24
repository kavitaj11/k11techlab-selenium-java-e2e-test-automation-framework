package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_04_03_fluent_wait;

import org.junit.jupiter.api.Assertions;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitExampleTest extends BaseSeleniumTest {

    /*
        WebDriverWait is built on top of FluentWait,
        we can use this to wait on anything, not just WebDriver
    */

    WebDriver driver;

    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://eviltester.github.io/supportclasses/");
    }



    @Test
    public void explicitWaitIsFluent(){

        final WebElement resendButton = driver.findElement(By.id("resend-select"));
        resendButton.click();

        // WebDriverWait is built on FluentWait so we have a lot of control over the wait
        // todo: customise timeout message, poll every 50 milliseconds,
        //       and ignore StaleElementReferenceException.class
        final WebElement message = new WebDriverWait(driver, Duration.ofSeconds(5)).
                                        withMessage("Could not find a Message").
                                        pollingEvery(Duration.ofMillis(50)).
                                        ignoring(StaleElementReferenceException.class).
                                            until(ExpectedConditions.
                                                visibilityOfElementLocated(
                                                        By.cssSelector("#single-list li.message")));

        Assertions.assertTrue(message.getText().startsWith("Received message:"));

    }

    // using fluent wait to wait using WebElement rather than driver
    @Test
    public void usingFluentWait(){

        final WebElement resendButton = driver.findElement(By.id("resend-select"));
        resendButton.click();

        WebElement singleListParent = driver.findElement(By.id("single-list"));
        FluentWait wait = new FluentWait<WebElement>(singleListParent).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofMillis(500)).
                withMessage("Could not find any new messages");

        wait.until(
            new HistoryMessagesIncreaseInNumber(0)
        );

        final WebElement message = driver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertTrue(message.getText().startsWith("Received message:"));

    }

    private class HistoryMessagesIncreaseInNumber implements Function<WebElement, Boolean> {
        private final int initialCount;

        public HistoryMessagesIncreaseInNumber(int initialCount) {
            this.initialCount = initialCount;
        }

        @Override
        public Boolean apply(final WebElement element) {
            int currentCount = element.findElements(By.cssSelector("li.message")).size();
            return currentCount>initialCount;
        }
    }




}
