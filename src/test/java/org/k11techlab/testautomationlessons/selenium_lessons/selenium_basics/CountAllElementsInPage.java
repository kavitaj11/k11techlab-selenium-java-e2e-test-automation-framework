package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics;

import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class CountAllElementsInPage extends BaseSeleniumTest {

    WebDriver driver;


    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://demo.nopcommerce.com");
    }


    @Test
    // Find total number of links on a web page
    public void GetAllLinks() throws InterruptedException {
        List<WebElement> list = driver.findElements(By.tagName("a"));
        System.out.println("links count: " + list.size());
        for (WebElement webEl : list) {
            System.out.println(webEl.getText());
            // Using Assert to verify expected number of links vs. actual result
          }
        Assert.assertEquals("links count: 86", "links count: " + list.size());

    }

    @Test
    public void GetAllRadioButtons() throws InterruptedException {
        // Find total number of radio buttons on a web page
        List<WebElement> radio = driver.findElements(By.xpath("//input[@type='radio']"));
            System.out.println("Total number of radio buttons: " + radio.size());
        // Using Assert to verify expected number of Radio Buttons vs. actual result
        Assert.assertEquals("radio count: 4", "radio count: " + radio.size());
    }

    @Test
    public void GetAllTextBoxs() throws InterruptedException {
        // Find total number of Textbox on a web page
        List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));
            System.out.println("Total number of Textbox: " + textboxes.size());
        // Using Assert to verify expected number of Text Boxes vs. actual result
        Assert.assertEquals("Textbox count: 1", "Textbox count: " + textboxes.size());

    }

    @Test
    public void GetAllMenus() throws InterruptedException {
        // Find total number of Menus on a web page
        List<WebElement> dropdown = driver.findElements(By.tagName("select"));
            System.out.println("Total number of Menus: " + dropdown.size());
        // Using Assert to verify expected number of Text Boxes vs. actual result
        Assert.assertEquals("dropdown count: 1", "dropdown count: " + dropdown.size());
    }

}