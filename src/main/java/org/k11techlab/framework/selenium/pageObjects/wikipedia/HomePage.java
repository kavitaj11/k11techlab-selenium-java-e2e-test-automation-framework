package org.k11techlab.framework.selenium.pageObjects.wikipedia;


import org.k11techlab.framework.selenium.webuitestbase.BaseTestPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseTestPage {
    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ArticlePage searchForArticle(String articleName) {
        searchInput.sendKeys(articleName);
        searchButton.click();
        return new ArticlePage(driver);
    }
}
