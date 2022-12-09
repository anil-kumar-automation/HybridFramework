package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExplicitWaiting {
    WebDriver driver;

    /**
     * To Wait Until Element to be Clickable
     */
    public void explicitWaitElementToBeClickable(WebElement element, long timeInSeconds) {
        WebDriverWait clickableWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * To Wait Until Element to be Selectable
     */
    public void explicitWaitElementToBeSelected(WebElement element, long timeInSeconds) {
        WebDriverWait selectableWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        selectableWait.until(ExpectedConditions.elementToBeSelected(element));
    }


    /**
     * To Wait Until Element has the text
     */
    public void explicitWaitTextToBePresentInElement(WebElement element, long timeInSeconds, String text) {
        WebDriverWait textToBePresent = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        textToBePresent.until(ExpectedConditions.textToBePresentInElement(element, text));
    }


    /**
     * To Wait Until Title contains the text
     */
    public void explicitWaitTitleContains(WebElement element, long timeInSeconds, String title) {
        WebDriverWait titleContains = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        titleContains.until(ExpectedConditions.titleContains(title));
    }


    /**
     * To Wait Until Title is
     */
    public void explicitWaitTitleIs(WebElement element, long timeInSeconds, String title) {
        WebDriverWait titleIs = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /**
     * To Wait Until Element to be Visible
     */
    public void explicitWaitVisibilityOfElement(WebElement element, long timeInSeconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * To Wait Until Element is Selected
     */
    public void explicitWaitSelectionStateToBe(WebElement element, long timeInSeconds, boolean selected) {
        WebDriverWait elementIsSelected = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        elementIsSelected.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }


    /**
     * To Wait Until Elements to be Visible
     */
    public void explicitWaitVisibilityOfElements(List<WebElement> element, long timeInSeconds) {
        WebDriverWait elementsToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        elementsToBeVisible.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    /*Select using By Method*/

    /**
     * To Wait Until Element to be Clickable
     */
    public void explicitWaitElementToBeClickable(By element, long timeInSeconds) {
        WebDriverWait clickableWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * To Wait Until Element to be Selectable
     */
    public void explicitWaitElementToBeSelected(By element, long timeInSeconds) {
        WebDriverWait selectableWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        selectableWait.until(ExpectedConditions.elementToBeSelected(element));
    }


    /**
     * To Wait Until Title contains the text
     */
    public void explicitWaitTitleContains(By element, long timeInSeconds, String title) {
        WebDriverWait titleContains = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        titleContains.until(ExpectedConditions.titleContains(title));
    }


    /**
     * To Wait Until Title is
     */
    public void explicitWaitTitleIs(By element, long timeInSeconds, String title) {
        WebDriverWait titleIs = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /**
     * To Wait Until Element to be Visible
     */
    public void explicitWaitVisibilityOfElement(By element, long timeInSeconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    /**
     * To Wait Until Element is Selected
     */
    public void explicitWaitSelectionStateToBe(By element, long timeInSeconds, boolean selected) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        elementToBeVisible.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    /**
     * To Wait for the Alert
     */
    public void explicitWaitForAlert(long timeInSeconds) {
        WebDriverWait waitForAlert = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        waitForAlert.until(ExpectedConditions.alertIsPresent());
    }
}