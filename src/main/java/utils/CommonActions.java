package utils;

import BrowserFactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CommonActions {

    /* navigate Efrom application*/
    public void navigateEformmurl() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.properties");
        prop.load(fis);
        String url1 = prop.getProperty("url1");
        DriverFactory.getDriver().get(url1);

    }

    /* navigate snapdeal application*/
    public void navigateSanpdealurl() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.properties");
        prop.load(fis);
        String url = prop.getProperty("url");
        DriverFactory.getDriver().get(url);
    }

    /* To click a certain Web Element */
    public void clickingOnWebElement(WebElement element, long waitTimeInSeconds) {
        //Declare and initialise a fluent wait
        FluentWait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver());
        //Specify the timout of the wait
        wait.withTimeout(Duration.ofSeconds(waitTimeInSeconds));
        //Sepcify polling time
        wait.pollingEvery(Duration.ofSeconds((long) 2.5));
        //Specify what exceptions to ignore
        wait.ignoring(NoSuchElementException.class);

        //This is how we specify the condition to wait on.
        //This is what we will explore more in this chapter
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    /* To Type at the specified location */
    public void sendKeysWebElement(WebElement element, String text) throws InterruptedException {
        //Declare and initialise a fluent wait
        FluentWait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver());
        //Specify the timout of the wait
        wait.withTimeout(Duration.ofSeconds(3));
        //Sepcify polling time
        wait.pollingEvery(Duration.ofSeconds((long) 2.5));
        //Specify what exceptions to ignore
        wait.ignoring(NoSuchElementException.class);

        //This is how we specify the condition to wait on.
        //This is what we will explore more in this chapter
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    /* Wait for element clickable */
    public WebElement waitForElement(WebElement elementName, long waitTimeSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(waitTimeSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(elementName));
        return elementName;
    }

    /* to wait until element to be visible */
    public void explicitWaitVisibilityOfElement(By element, long timeInSeconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeInSeconds));
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    /* To ScrollDown using JavaScript Executor */
    public void scrollDown(String valueTobeSelected) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getDriver();
        jse.executeScript(valueTobeSelected);
    }

    /* To ScrollUp using JavaScript Executor */
    public void scrollUp(String valueTobeSelected) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getDriver();
        jse.executeScript(valueTobeSelected);
    }

    /* To Type at the specified location before it clear the present text  */
    public void sendKeysAndClearClick(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    /* To click a certain Web Element using DOM/ JavaScript Executor */
    public void javaScriptExecutorClick(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("return arguments[0].click();", element);
    }

    public void sendkeysJavaScript(WebElement element, String text) {
        WebElement ele = waitForElement(element, 20);
        JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getDriver());
        js.executeScript("arguments[0].value='" + text + "';", ele);
    }


    /* To Perform Select all Dropdown Option's  */
    public void selectDropDownValue(WebElement element, String type, String value) {
        Select select = new Select(element);
        switch (type) {
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibletext":
                select.selectByVisibleText(value);
                break;
            default:
                System.out.println("pass the correct selection criteria");
                break;
        }
    }

    /* To Perform Select Option by VisibleText */
    public void doselectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /* To Perform Select Option by Index */
    public void doselectDropDownByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    /* To Perform Select Option by Value */
    public void doselectDropDownByValue(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByValue(text);
    }

    /*To click radio button */
    public void selectRadioButtonValue(List<WebElement> element, String valueTobeSelected) {
        for (WebElement ref : element) {
            ref.click();
            break;
        }
    }

    /*To Close All Tab */
    public void tearDown() {
        DriverFactory.getDriver().quit();
    }

    /* To Clear the content in the input location */
    public void clear(WebElement element) {
        element.clear();
    }


    /* To Accept the Alert Dialog Message */
    public void alertAccept() throws Exception {
        Alert al = DriverFactory.getDriver().switchTo().alert();
        al.accept();
    }


    /* To Dismiss the Alert Dialog Message */
    public void alertDismiss() throws Exception {
        Alert al = DriverFactory.getDriver().switchTo().alert();
        al.dismiss();
    }

    //accepting an alert From UI
    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    /* To Get the Alert Messages */
    public String getAlertText() throws Exception {
        Alert al = DriverFactory.getDriver().switchTo().alert();
        String text = al.getText();
        Thread.sleep(2000);
        alertAccept();
        return text;
    }


    /* To Perform a WebAction of Mouse Over */
    public void mouseHover(WebElement element) {
        Actions ac = new Actions(DriverFactory.getDriver());
        ac.moveToElement(element).build().perform();
    }

    /* To Drag and Drop from Source Locator to Destination Locator */
    public void dragAndDrop(WebElement Source, WebElement Destination) {
        Actions ac = new Actions(DriverFactory.getDriver());
        ac.dragAndDrop(Source, Destination);
    }


    /*To Drag from the given WebElement Location and Drop at the given WebElement location */
    public void dragAndDropTo(WebElement Source, int XOffset, int YOffset) throws Exception {
        Actions ac = new Actions(DriverFactory.getDriver());
        ac.dragAndDropBy(Source, XOffset, YOffset);
    }

    /*To Open a Page in New Tab */
    public void rightClick(WebElement element) {
        Actions ac = new Actions(DriverFactory.getDriver());
        ac.contextClick(element);
        ac.build().perform();
    }


    /*To Perform Click and Hold Action */
    public void clickAndHold(WebElement element) {
        Actions ac = new Actions(DriverFactory.getDriver());
        ac.clickAndHold(element);
        ac.build().perform();
    }


    /*To Perform Click and Hold Action */
    public void doubleClick(WebElement element) {
        Actions ac = new Actions(DriverFactory.getDriver());
        ac.doubleClick(element);
        ac.build().perform();
    }

    /*To Switch To Frame By Index */
    public void switchToFrameByIndex(int index) throws Exception {
        DriverFactory.getDriver().switchTo().frame(index);
    }


    /*To Switch To Frame By Frame Name */
    public void switchToFrameByFrameName(String frameName) throws Exception {
        DriverFactory.getDriver().switchTo().frame(frameName);
    }


    /*To Switch To Frame By Web Element */
    public void switchToFrameByWebElement(WebElement element) throws Exception {
        DriverFactory.getDriver().switchTo().frame(element);
    }


    /*To Switch out of a Frame */
    public void switchOutOfFrame() throws Exception {
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    /*To Switch out of a Frame */
    public void windowHandler() throws Exception {
        Set<String> windows = DriverFactory.getDriver().getWindowHandles(); //[parentid,childid,subchildId]
        Iterator<String> it = windows.iterator();
        String parentId = it.next();
        String childId = it.next();
        DriverFactory.getDriver().switchTo().window(childId);

    }


    /*Calendar it helps to select future data */
    public void selectCalendar(String expectDay, String expectMonth, String expectYear) {
        if (expectMonth.equals("February") && Integer.parseInt(expectDay) > 29) {
            System.out.println("wrong date: " + expectMonth + " : " + expectDay);
            return;
        }
        if (Integer.parseInt(expectDay) > 31) {
            System.out.println("wrong date: " + expectMonth + " : " + expectDay);
            return;
        }

        String monthvalue = DriverFactory.getDriver().findElement(By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p")).getText();
        String yearvalue = DriverFactory.getDriver().findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p")).getText();
        System.out.println(monthvalue);
        System.out.println(yearvalue);

        while (!(monthvalue.equals(expectMonth) && yearvalue.equals(expectYear))) {
            DriverFactory.getDriver().findElement(By.cssSelector("i.oxd-icon.bi-chevron-right")).click(); //clicking next action
            monthvalue = DriverFactory.getDriver().findElement(By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p")).getText();
            yearvalue = DriverFactory.getDriver().findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p")).getText();
        }
        int count = DriverFactory.getDriver().findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).size();
        for (int i = 0; i < count; i++) {
            String text = DriverFactory.getDriver().findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).get(i).getText();
            if (text.equalsIgnoreCase(expectDay)) {
                DriverFactory.getDriver().findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).get(i).click();
                break;
            }
        }
    }
}
