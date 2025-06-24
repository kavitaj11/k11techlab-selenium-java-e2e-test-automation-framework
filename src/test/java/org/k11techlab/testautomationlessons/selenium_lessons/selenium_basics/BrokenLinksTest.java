package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics;

import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class BrokenLinksTest extends BaseSeleniumTest {
    WebDriver driver = getDriver();

       @Test
       public void BrokenLinksTest(){

       driver.get("http://example.com");
       List<WebElement> links = driver.findElements(By.tagName("a"));
       for (WebElement link : links) {
           String url = link.getAttribute("href");
           try {
               HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
               connection.setRequestMethod("HEAD");
               connection.connect();
               int responseCode = connection.getResponseCode();
               if (responseCode >= 400) {
                   System.out.println(url + " is a broken link.");
               } else {
                   System.out.println(url + " is a valid link.");
               }
           } catch (Exception e) {
               System.out.println(url + " is a broken link.");
           }
       }
       driver.quit();
   }
}
