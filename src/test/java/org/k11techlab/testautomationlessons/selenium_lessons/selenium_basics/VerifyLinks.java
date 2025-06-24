package org.k11techlab.testautomationlessons.selenium_lessons.selenium_basics;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class VerifyLinks extends BaseSeleniumTest {

    WebDriver driver;


    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }


    @Test
    public void VerifyLinks() {
        // Get all the links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Print the total number of links found
        System.out.println("Total links found: " + links.size());

        // Create an HttpClient instance
        HttpClient client = HttpClientBuilder.create().build();

        // Check each link
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                try {
                    // Create an HTTP GET request
                    HttpGet request = new HttpGet(url);

                    // Execute the request and get the response
                    HttpResponse response = client.execute(request);

                    // Check the response code
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        System.out.println("Link is valid: " + url);
                    } else {
                        System.out.println("Broken link found: " + url + " (Response code: " + statusCode + ")");
                    }
                } catch (IOException e) {
                    System.out.println("Error checking link: " + url + " (" + e.getMessage() + ")");
                }
            }
        }

    }
}
