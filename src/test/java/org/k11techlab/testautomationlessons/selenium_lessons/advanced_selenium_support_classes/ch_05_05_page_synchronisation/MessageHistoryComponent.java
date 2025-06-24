package org.k11techlab.testautomationlessons.selenium_lessons.advanced_selenium_support_classes.ch_05_05_page_synchronisation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MessageHistoryComponent {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "message-history")
    private WebElement messageHistoryContainer;

    @FindBy(css = ".history-message")
    private List<WebElement> historyMessages;

    public MessageHistoryComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for the message history component to be ready.
     * Ensures that the container is visible and contains at least one message.
     */
    public void waitTillReady() {
        // Wait for the message history container to be visible
        wait.until(ExpectedConditions.visibilityOf(messageHistoryContainer));

        // Wait for at least one message to be present in the history
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector(".history-message"), 0));

        System.out.println("MessageHistoryComponent is ready.");
    }

    /**
     * Counts the total number of history messages displayed.
     *
     * @return the number of messages in the history.
     */
    public int countSingleHistoryMessages() {
        return historyMessages.size();
    }

    /**
     * Gets the text of a specific history message by index.
     *
     * @param index the zero-based index of the message.
     * @return the text of the history message.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public String getSingleHistoryMessage(int index) {
        if (index < 0 || index >= historyMessages.size()) {
            throw new IndexOutOfBoundsException("Invalid message index: " + index);
        }
        return historyMessages.get(index).getText();
    }
}
