package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_05_05_page_synchronisation;

import org.junit.jupiter.api.Assertions;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageSyncObjectTest  extends BaseSeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://eviltester.github.io/supportclasses/");
    }


    @Test
    public void canSeeMessageInHistory(){

        // to show a component on the page
        LoadableSupportPage page = new LoadableSupportPage(driver);

        // we should really wait for page to load prior to doing anything,
        // to make sure it is valid to work with
        page.get();

        page.select("Option 2");
        Assertions.assertEquals("Received message: selected 2",
                                page.getMessage());
        MessageHistoryComponent history = page.messageHistory();

        // wait for the history component to be ready
        history.waitTillReady();

        Assertions.assertEquals(1, history.countSingleHistoryMessages());
        Assertions.assertEquals("Received message: selected 2",
                                    history.getSingleHistoryMessage(0));
    }

}
