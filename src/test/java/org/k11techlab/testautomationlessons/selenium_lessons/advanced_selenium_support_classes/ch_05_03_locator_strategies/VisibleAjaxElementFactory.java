package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_05_03_locator_strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;

public class VisibleAjaxElementFactory implements ElementLocatorFactory {
    private final WebDriver driver;
    private final int timeout;

    public VisibleAjaxElementFactory(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new VisibleAjaxElementLocator(driver, field, timeout);
    }

    private static class VisibleAjaxElementLocator extends AjaxElementLocator {
        private final WebDriver driver;
        private final int timeout;

        public VisibleAjaxElementLocator(WebDriver driver, Field field, int timeout) {
            super(driver, field, timeout);
            this.driver = driver;
            this.timeout = timeout;
        }

        @Override
        public WebElement findElement() {
            WebElement element = super.findElement();
            new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
            return element;
        }
    }
}
