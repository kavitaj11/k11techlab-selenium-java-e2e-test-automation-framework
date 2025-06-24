package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_05_04_pros_cons;

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
    public void sendMessageWithWaitInPageObject(){

        SupportClassesPage page = new SupportClassesPage(driver);
        Assertions.assertEquals(0, page.countSingleMessageHistory());
        page.clickResendSingleButton();

        Assertions.assertEquals("Received message: selected 1",
                                page.waitForMessage());
        Assertions.assertEquals(1, page.countSingleMessageHistory());
    }

}
