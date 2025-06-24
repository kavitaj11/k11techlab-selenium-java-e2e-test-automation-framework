package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_02_02_custom;



import org.junit.jupiter.api.Assertions;
import org.k11techlab.framework.selenium.webuitestbase.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateButtonAbstractionTest extends BaseSeleniumTest {

    static WebDriver driver;

    @BeforeMethod
    public void start(){
        driver= getDriver();
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://eviltester.github.io/supportclasses/");
    }


    @Test
    public void canClickAButton(){

        final WebElement buttonElement = driver.findElement(By.id("resend-select"));
        Button button = new Button(buttonElement);
        // rather than click on a button element,
        // could we click on a Button?
        Assertions.assertEquals("Resend Single Option Message",
                                    button.getText());

        button.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.textToBe(By.id("message"),
                "Received message: selected 1"));
    }



    private class Button implements WrapsElement{
        private final WebElement button;

        public Button(WebElement buttonElement) {
            this.button = buttonElement;
        }

        @Override
        public WebElement getWrappedElement() {
            return button;
        }

        public String getText() {
            return button.getText();
        }

        public void click() {
            button.click();
        }
    }
}
