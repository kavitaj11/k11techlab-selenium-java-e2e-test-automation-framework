package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_01_01_overview;

import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class InitialTest extends BaseSeleniumTest {

    WebDriver driver=getDriver();

    @Test
    public void anInitialTest() {
        driver.get("https://eviltester.github.io/supportclasses/");
        Assert.assertEquals(driver.getTitle().equals("Support Classes Example"),true);
    }

}
