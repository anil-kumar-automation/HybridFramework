package utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CommonActions{
    public static WebDriver driver;

    /* To launching browser with user input */
    public void launchBrowser(String browser) throws IOException {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("InternetExplorer")) {
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions ieo = new InternetExplorerOptions();
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions fo = new FirefoxOptions();
            fo.setHeadless(false);
            driver = new FirefoxDriver(fo);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    /* navigate Efrom application*/
    public void navigateEfromurl() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.Properties");
        prop.load(fis);
        String url1 = prop.getProperty("url1");
        driver.get(url1);

    }

    /* navigate snapdeal application*/
    public void navigateSanpdealurl() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.Properties");
        prop.load(fis);
        String url = prop.getProperty("url");
        driver.get(url);
    }

    /* To click a certain Web Element */
    public void clickingOnWebElement(WebElement element, long waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //For sendkeys Action
    public void sendKeysWebElement(WebElement element, String text) {
        waitForElement(element, 5);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public WebElement waitForElement(WebElement elementName, long waitTimeSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementName));
        return element;
    }


    /* To Type at the specified location before it clear the present text  */
    public void sendKeysAndClearClick(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    /*To Wait Until Element to be Visible*/
    public void explicitWaitVisibilityOfElement(By element, long timeInSeconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /*To Wait Until Element to be Clickable*/
    public void explicitWaitElementToBeClickable(By element, long timeInSeconds) {
        WebDriverWait clickableWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /* To ScrollDown using JavaScript Executor */
    public void scrollDown(String valueTobeSelected) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(valueTobeSelected);
    }

    /* To ScrollUp using JavaScript Executor */
    public void scrollUp(String valueTobeSelected) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(valueTobeSelected);
    }

    /* To click a certain Web Element using DOM/ JavaScript Executor */
    public void javaScriptExecutorClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("return arguments[0].click();", element);
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
        driver.quit();
    }

    /* To Clear the content in the input location */
    public void clear(WebElement element) {
        element.clear();
    }


    /* To Accept the Alert Dialog Message */
    public void alertAccept() throws Exception {
        Alert al = driver.switchTo().alert();
        al.accept();
    }


    /* To Dismiss the Alert Dialog Message */
    public void alertDismiss() throws Exception {
        Alert al = driver.switchTo().alert();
        al.dismiss();
    }

    //accepting an alert From UI
    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    /* To Get the Alert Messages */
    public String getAlertText() throws Exception {
        Alert al = driver.switchTo().alert();
        String text = al.getText();
        Thread.sleep(2000);
        alertAccept();
        return text;
    }


    /* To Perform a WebAction of Mouse Over */
    public void mouseHover(WebElement element) {
        Actions ac = new Actions(driver);
        ac.moveToElement(element).build().perform();
    }

    /* To Drag and Drop from Source Locator to Destination Locator */
    public void dragAndDrop(WebElement Source, WebElement Destination) {
        Actions ac = new Actions(driver);
        ac.dragAndDrop(Source, Destination);
    }


    /*To Drag from the given WebElement Location and Drop at the given WebElement location */
    public void dragAndDropTo(WebElement Source, int XOffset, int YOffset) throws Exception {
        Actions ac = new Actions(driver);
        ac.dragAndDropBy(Source, XOffset, YOffset);
    }

    /*To Open a Page in New Tab */
    public void rightClick(WebElement element) {
        Actions ac = new Actions(driver);
        ac.contextClick(element);
        ac.build().perform();
    }


    /*To Perform Click and Hold Action */
    public void clickAndHold(WebElement element) {
        Actions ac = new Actions(driver);
        ac.clickAndHold(element);
        ac.build().perform();
    }


    /*To Perform Click and Hold Action */
    public void doubleClick(WebElement element) {
        Actions ac = new Actions(driver);
        ac.doubleClick(element);
        ac.build().perform();
    }

    /*To Switch To Frame By Index */
    public void switchToFrameByIndex(int index) throws Exception {
        driver.switchTo().frame(index);
    }


    /*To Switch To Frame By Frame Name */
    public void switchToFrameByFrameName(String frameName) throws Exception {
        driver.switchTo().frame(frameName);
    }


    /*To Switch To Frame By Web Element */
    public void switchToFrameByWebElement(WebElement element) throws Exception {
        driver.switchTo().frame(element);
    }


    /*To Switch out of a Frame */
    public void switchOutOfFrame() throws Exception {
        driver.switchTo().defaultContent();
    }

    /*To Switch out of a Frame */
    public void windowHandler() throws Exception {
        Set<String> windows = driver.getWindowHandles(); //[parentid,childid,subchildId]
        Iterator<String> it = windows.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);

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

        String monthvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p")).getText();
        String yearvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p")).getText();
        System.out.println(monthvalue);
        System.out.println(yearvalue);

        while (!(monthvalue.equals(expectMonth) && yearvalue.equals(expectYear))) {
            driver.findElement(By.cssSelector("i.oxd-icon.bi-chevron-right")).click(); //clicking next action
            monthvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p")).getText();
            yearvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p")).getText();
        }
        int count = driver.findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).size();
        for (int i = 0; i < count; i++) {
            String text = driver.findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).get(i).getText();
            if (text.equalsIgnoreCase(expectDay)) {
                driver.findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).get(i).click();
                break;
            }
        }
    }


}
