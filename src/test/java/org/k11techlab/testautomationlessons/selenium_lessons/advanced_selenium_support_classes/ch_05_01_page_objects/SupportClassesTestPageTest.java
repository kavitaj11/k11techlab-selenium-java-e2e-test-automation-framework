package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_05_01_page_objects;

import org.junit.jupiter.api.Assertions;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SupportClassesTestPageTest extends BaseSeleniumTest {

    static WebDriver driver;

    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://eviltester.github.io/supportclasses/");
    }


    /*
        Test using abstractions is:

        - easy to read
        - only has to change if intent of test changes
        - does not have to change if application changes - the page objects change
        - page objects used in multiple tests

     */
    @Test
    public void canSendMessage(){

        SupportClassesTestPage page = new SupportClassesTestPage(driver);

        page.selectSingleOptionMessage("Option 2");

        page.waitForMessageReceived();

        Assertions.assertEquals("Received message: selected 2",
                                page.getLastSingleMessage());

    }
}
