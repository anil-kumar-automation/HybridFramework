package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AddEmployeeForHrmPage  {
    WebDriver driver;
    @FindBy(xpath = "//nav[@class='oxd-topbar-body-nav']/ul/li[3]")
    WebElement Add_Emp;

    @FindBy(xpath = "//button[@class='oxd-icon-button employee-image-action']")
    WebElement Add_pic;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[2]/a/span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
    WebElement PIM;

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

    @FindBy(xpath = "//div[@class='oxd-select-option']/span")
    List<WebElement> maritalstatusDropdownListItem;

    @FindBy(xpath = "//form[@class='oxd-form']/div[3]/div[2]/div[1]/div/div[2]/div/div/i")
    WebElement DobEmpListCalendar;

    @FindBy(xpath = "//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']")
    List<WebElement> GenderRadiobutton;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space'][1]/div[2]/div[@class='oxd-checkbox-wrapper']/label/input")
    WebElement smokerCheckbox;

    @FindBy(xpath = "//form[@class='oxd-form']/div[5]/button[@type='submit']")
    WebElement saveButtonOnPersonalDetails;


    /*initializing the page objects*/
    public AddEmployeeForHrmPage(WebDriver rDriver) {
        driver = rDriver;
        PageFactory.initElements(rDriver, this);
    }


    public void clickPIM() {
        CommonActions.clickingOnWebElement(PIM, 2);

    }

    public void clickAddEmployee() {
        CommonActions.clickingOnWebElement(Add_Emp, 2);
    }

    public void AddEmployeeActions() throws AWTException, IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.Properties");
        Properties prop = new Properties();
        prop.load(fis);
       /* clickingOnWebElement(Add_pic, 2);
        Robot robot = new Robot();
        robot.setAutoDelay(4000);
        StringSelection stringSelection = new StringSelection("C:\\Users\\as61837\\Documents\\GitHub\\HybridFramework\\HybridFramework\\download.png");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); //copy
        robot.setAutoDelay(4000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(3000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);*/

        CommonActions.sendKeysWebElement(FirstName, prop.getProperty("fname"));
        CommonActions.sendKeysWebElement(MiddleName, prop.getProperty("mname"));
        CommonActions.sendKeysWebElement(LastName, prop.getProperty("lname"));

        CommonActions.clickingOnWebElement(Save, 3);
    }

    public void AddEmployeeActionsDb(String fName, String mName,String lName) throws InterruptedException {
        CommonActions.sendKeysWebElement(FirstName, fName);
        CommonActions.sendKeysWebElement(MiddleName, mName);
        CommonActions.sendKeysWebElement(LastName, lName);
        CommonActions.clickingOnWebElement(Save, 3);
    }
    public void scrool() throws InterruptedException {
        CommonActions.scrollDown("window.scrollBy(0, 500);");
    }


    public void dropdown() throws InterruptedException {
        CommonActions.clickingOnWebElement(Nationalityclickdropdown, 3);
        // List<WebElement> options = driver.findElements(By.cssSelector("div[class='oxd-select-option'] span"));
        for (WebElement option : nationalitydropdwonListitem) {
            if (option.getText().equalsIgnoreCase("Indian")) {
                option.click();
                break;
            }
        }
        CommonActions.clickingOnWebElement(maritalstatusclickdropdown, 3);
        // List<WebElement> options = driver.findElements(By.cssSelector("div[class='oxd-select-option'] span"));
        for (WebElement option : maritalstatusDropdownListItem) {
            if (option.getText().equalsIgnoreCase("Single")) {
                option.click();
                break;
            }
        }
    }

    public void setData() throws InterruptedException {
        CommonActions.clickingOnWebElement(DobEmpListCalendar, 2);
        Thread.sleep(2000);
        CommonActions.selectCalendar("20", "December", "2023");

    }

    public void SelectRadioButton() {
        CommonActions.selectRadioButtonValue(GenderRadiobutton,"Male");
    }

    public void SelectCheckboxAndSave() {
        CommonActions.clickingOnWebElement(smokerCheckbox, 2);
        CommonActions.clickingOnWebElement(saveButtonOnPersonalDetails, 2);
    }
}


