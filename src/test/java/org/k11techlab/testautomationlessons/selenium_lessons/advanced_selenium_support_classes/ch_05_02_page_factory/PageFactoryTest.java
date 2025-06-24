package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_05_02_page_factory;

import org.junit.jupiter.api.Assertions;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PageFactoryTest extends BaseSeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://eviltester.github.io/supportclasses/");
    }


    @Test
    public void sendMessage(){

        SupportClassesPage page = new SupportClassesPage(driver);

        page.singleResendButton.click();

//        Assertions.assertEquals("Received message: selected 1",
//         page.message.getText());
    }

    // the default most people use for handling timeout issues with
    // page factory is implicit waits

    @Test
    public void sendMessageWithWaitInPageObject(){

        SupportClassesPage page = new SupportClassesPage(driver);

        page.singleResendButton.click();

        Assertions.assertEquals("Received message: selected 1",
                page.waitForMessage());
    }

}
