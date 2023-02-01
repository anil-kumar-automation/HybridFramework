package page;

import BrowserFactory.DriverFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.selenium.CommonActions;
import utils.selenium.JsonRead;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class EfromAdditionSoftwareRequestPageJson {
    WebDriver driver;

    /* it's finding menu element in eform Application */
    @FindBy(xpath = "//div[@class='noti__item js-item-menu']//img")
    WebElement menu;

    /* it's finding request type element in eform Application */
    @FindBy(xpath = "//a[@id='AdditionalSoftwareFormComponent']")
    WebElement type;

    /* it's finding project name element in eform Application */
    @FindBy(xpath = "//select[@id='projectName']")
    WebElement projectname;

    /* it's finding user element in eform Application */
    @FindBy(xpath = "//input[@id='radioUser1']")
    WebElement radiouser;

    /* it's finding asset type element in eform Application */
    @FindBy(xpath = "//input[@formcontrolname='ipAddress']")
    WebElement ipAddress;

    /* it's finding asset number element in eform Application */
    @FindBy(xpath = "//select[@id='assetNumber']")
    WebElement assetNumber;

    /* it's finding licence type element in eform Application */
    @FindBy(xpath = "//select[@formcontrolname='licenseType']")
    WebElement licenseType;

    /* it's finding  commercial element in eform Application */
    @FindBy(xpath = "//select[@formcontrolname='commercial']")
    WebElement freeware;

    /* it's finding asset number element in eform Application */
    @FindBy(xpath = "//input[@id='assetno']")
    WebElement assetNo;

    /* it's finding extension number element in eform Application */
    @FindBy(xpath = "//input[@id='extension']")
    WebElement extensionNumber;

    /* it's finding location element in eform Application */
    @FindBy(xpath = "//select[@id='location']")
    WebElement location;

    /* it's finding desk number element in eform Application */
    @FindBy(xpath = "//input[@id='deskNumber']")
    WebElement deskNumber;

    /* it's finding required date element in eform Application */
    @FindBy(xpath = "//input[@id='requiredDate']")
    WebElement requiredDate;

    /* it's finding revoke date element in eform Application */
    @FindBy(xpath = "//input[@formcontrolname='productDetails']")
    WebElement productName;

    /* it's finding hostname element in eform Application */
    @FindBy(xpath = "//input[@formcontrolname='hostName']")
    WebElement hostname;

    /* it's finding remark element in eform Application */
    @FindBy(xpath = "//textarea[@id='remarks']")
    WebElement remark;

    /* it's finding checkbox element in eform Application */
    @FindBy(xpath = "//input[@id='checkInternalPurpose']")
    WebElement internalProject;

    /* it's finding submit button element in eform Application */
    @FindBy(xpath = "//a[@class='au-btn--submit ml-4 btnToScroll ng-star-inserted']")
    WebElement submitbtn;

    /* it's finding terms and conditions element in eform Application */
    @FindBy(xpath = "//a[normalize-space()='Terms & Conditions']")
    WebElement tndc;

    /* it's finding checkbox element in eform Application */
    @FindBy(xpath = "//div[@id='okBtn']//input[@placeholder='Please enter details']")
    WebElement checkbox;

    /* it's finding ok button element in eform Application */
    @FindBy(xpath = "//button[normalize-space()='OK']")
    WebElement okbtn;

    public EfromAdditionSoftwareRequestPageJson(WebDriver rDriver) {
        driver = rDriver;

        PageFactory.initElements(rDriver, this);
    }
    /* This method is used to click menu and select request type */
    public void clickOnMenu() {
        CommonActions.clickingOnWebElement(menu, 5);
        CommonActions.clickingOnWebElement(type, 5);
    }

    /* This method is used to select respective project name and fill remaining details */
    public void enterRequireDetails() throws InterruptedException, IOException, ParseException {

        // parsing file "JSONExample.json"
        Object obj = new JSONParser()
                .parse(new FileReader(System.getProperty("user.dir") +"/src/test/resources/Data.json"));

        // typecasting obj to JSONObject
        JSONObject jsonObject = (JSONObject) obj;

        String projectName = JsonRead.getValue(jsonObject, "project");
        String ProductDetails = JsonRead.getValue(jsonObject, "productdetails");
        String AssetNumber = JsonRead.getValue(jsonObject, "assetnumber");
        String AssetNo = JsonRead.getValue(jsonObject, "assetno");
        String ExtensionNumber = JsonRead.getValue(jsonObject, "extensionnumber");
        String Location = JsonRead.getValue(jsonObject, "location");
        String DeskNumber = JsonRead.getValue(jsonObject, "desknumber");
        String Hostname = JsonRead.getValue(jsonObject, "hostname");
        String RequireDate = JsonRead.getValue(jsonObject, "requiredate");
        String LicenseType = JsonRead.getValue(jsonObject, "licenseType");
        String Commercial = JsonRead.getValue(jsonObject, "commercial");
        String IpAddress = JsonRead.getValue(jsonObject, "ipAddress");

        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        CommonActions.selectDropDownValue(projectname, "value", projectName);
        CommonActions.scrollDown("window.scrollBy(0, 430)");
        CommonActions.sendKeysWebElement(extensionNumber, ExtensionNumber);
        CommonActions.selectDropDownValue(location, "value", Location);
        CommonActions.selectDropDownValue(assetNumber, "Value", AssetNumber);
        CommonActions.sendKeysWebElement(assetNo, AssetNo);
        CommonActions.sendKeysWebElement(deskNumber, DeskNumber);
        CommonActions.clickingOnWebElement(radiouser, 3);
        CommonActions.sendKeysWebElement(ipAddress, IpAddress);
        CommonActions.sendKeysWebElement(hostname, Hostname);
        CommonActions.scrollDown("window.scrollBy(0, 430)");
        CommonActions.clickingOnWebElement(internalProject, 3);
        CommonActions.selectDropDownValue(licenseType, "index", LicenseType);
        CommonActions.selectDropDownValue(freeware, "index", Commercial);
        Thread.sleep(3000);
        //requiredDate.sendKeys(RequireDate);
        CommonActions.sendKeysWebElement(productName, ProductDetails);
    }

    /* This method is used to accept terms and conditions */
    public void acceptTndC() throws InterruptedException {
        CommonActions.clickingOnWebElement(tndc, 3);
        Thread.sleep(3000);
        CommonActions.clickingOnWebElement(checkbox, 5);
        CommonActions.clickingOnWebElement(okbtn, 3);
    }

    /* This method is used to fill remark details */
    public void enterRemark() throws IOException, ParseException, InterruptedException {
        String Remark = "";
        // parsing file "JSONExample.json"
        Object obj = new JSONParser()
                .parse(new FileReader(System.getProperty("user.dir") +"/src/test/resources/Data.json"));
        // typecasting obj to JSONObject
        JSONObject jsonObject = (JSONObject) obj;

        Remark = JsonRead.getValue(jsonObject, "remark");
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        CommonActions.sendKeysWebElement(remark, Remark);
        CommonActions.scrollDown("window.scrollBy(0, 430)");

    }

    /* This method is used to click on submit button */
    public void clickOnSubmitbtn() {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        CommonActions.clickingOnWebElement(submitbtn, 2);
    }

}
