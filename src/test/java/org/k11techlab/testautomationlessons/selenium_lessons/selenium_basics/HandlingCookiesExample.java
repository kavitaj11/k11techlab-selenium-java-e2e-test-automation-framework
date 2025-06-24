package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics;

import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Maor on 4/17/2018.
 */
public class HandlingCookiesExample extends BaseSeleniumTest {

    WebDriver driver;


    @BeforeMethod
    public void start(){
        driver= getDriver();
        driver.get("https://stackoverflow.com");
    }

    @Test
    public void AddGetCookies() {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // Pass name and value for cookie as parameters
        Cookie name = new Cookie("seleniumtestcookie", "123456789123");
        driver.manage().addCookie(name);

        // To get our particular Cookie by name
        System.out.println(driver.manage().getCookieNamed("seleniumtestcookie").getValue());

        // To return all the cookies of the current domain
        Set<Cookie> cookiesForCurrentURL = driver.manage().getCookies();
        for (Cookie cookie : cookiesForCurrentURL) {
            System.out.println(" Cookie Name - " + cookie.getName()
                + " Cookie Value - "  + cookie.getValue());
            }
        }

    @Test
    public void deleteCookieByName()
    {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Delete our particular Cookie by name
        driver.manage().deleteCookieNamed("seleniumtestcookie");
    }

    @Test
    public void deleteAllCookies()
    {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

}
