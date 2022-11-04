package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Calendar;
import utils.CommonActions;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class AddEmployeePageForHrm extends CommonActions {

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
    WebElement Add_Emp;

    @FindBy(xpath = "//button[@class='oxd-icon-button employee-image-action']")
    WebElement Add_pic;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement FirstName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement MiddleName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement LastName;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement Save;

    @FindBy(css = "div[class='oxd-select-option'] span")
    List<WebElement> nationalitydropdwonListitem;

    @FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[1]/div/div[2]/div/div/div[2]")
    WebElement Nationalityclickdropdown;

    //personal details page
    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[1]")
    WebElement NationalitydropDown;


    //optional xapth
    @FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[2]/div/div[2]/div/div/div[1]")
    WebElement maritalstatus;

    @FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[1]/div[2]/div/div[2]/div/div/div[2]")
    WebElement maritalstatusclickdropdown;

    @FindBy(xpath = "//div[@class='oxd-select-option']/span") //correct
    List<WebElement> maritalstatusDropdownListItem;


    //form[@class='oxd-form']/div[3]/div[1]/div[2]/div/div[2]/div/div/div[2]


    @FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[2]/div[1]/div/div[2]/div/div/i")
    WebElement ClickCalendarTab;

    @FindBy(xpath = "//input[@value='1']")
    WebElement generMale;

    @FindBy(css = "div.--gender-grouped-field")
    WebElement generradiobutton;


    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']/div[2]/div/label/input[@type='checkbox']")
    WebElement smokerCheckbox;

    @FindBy(xpath = "//form[@class='oxd-form']/div[5]/button[@type='submit']")
    WebElement saveButtonOnPersonalDetails;

    public AddEmployeePageForHrm() {
        PageFactory.initElements(driver, this);
    }

    public void clickAddEmployee() {
        clickingOnWebElement(Add_Emp, 5);
    }

    public void AddEmployeeActions() throws AWTException, IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.Properties");
        Properties prop = new Properties();
        prop.load(fis);
      /*  clickingOnWebElement(Add_pic, 5);
        Robot robot = new Robot();
        robot.setAutoDelay(3000);
        StringSelection stringSelection = new StringSelection("C:\\Users\\as61837\\Documents\\GitHub\\TDDframework\\download.png");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); //copy
        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(2000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);*/
        /*Thread.sleep(5000);*/
        sendKeysWebElement(FirstName, prop.getProperty("fname"));
        Thread.sleep(1000);
        sendKeysWebElement(MiddleName, prop.getProperty("mname"));
        Thread.sleep(1000);
        sendKeysWebElement(LastName, prop.getProperty("lname"));
        Thread.sleep(1000);
        clickingOnWebElement(Save, 5);
    }

    public void scrool() throws InterruptedException {
        scrollDown("window.scrollBy(0, 400);");
        Thread.sleep(2000);
    }


    public void dropdown() throws InterruptedException {
        clickingOnWebElement(Nationalityclickdropdown, 5);
        // List<WebElement> options = driver.findElements(By.cssSelector("div[class='oxd-select-option'] span"));
        for (WebElement option : nationalitydropdwonListitem) {
            if (option.getText().equalsIgnoreCase("Indian")) {
                option.click();
                break;
            }
        }
        clickingOnWebElement(maritalstatusclickdropdown, 5);
        // List<WebElement> options = driver.findElements(By.cssSelector("div[class='oxd-select-option'] span"));
        for (WebElement option : maritalstatusDropdownListItem) {
            if (option.getText().equalsIgnoreCase("Single")) {
                option.click();
                break;
            }
        }
        Thread.sleep(5000);
    }

    public void setCalendar() {
        clickingOnWebElement(ClickCalendarTab, 5);
        selectCalendar("20", "November", "2026");
    }

    public void PersonalDetailsActions() throws InterruptedException {
        selectRadioButtonValue(Collections.singletonList(generradiobutton), "Male");
        Thread.sleep(5000);
        /*clickingOnWebElement(smokerCheckbox, 5);
        clickingOnWebElement(saveButtonOnPersonalDetails, 5);*/
    }


}
