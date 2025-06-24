package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_06_01_event_firing;

import org.junit.jupiter.api.Assertions;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExampleEventFiringTest extends BaseSeleniumTest {

    WebDriver driver;


    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://eviltester.github.io/supportclasses/");
    }


    @Test
    public void loggingFindingElements() {

        final By resend = By.id("resend-select");
        final By noSuchElement = By.id("no-such-element");

        EventFiringWebDriver events = new EventFiringWebDriver(driver);
        events.register(new LocalEventFiringListener());

        WebElement resendElem = events.findElement(resend);
        Assertions.assertNotNull(resendElem);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            events.findElement(noSuchElement);
        });

    }



    private class LocalEventFiringListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Looking For " + by.toString());
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Finished looking for " + by.toString());
        }
    }
}
