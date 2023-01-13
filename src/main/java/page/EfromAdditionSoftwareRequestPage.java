package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.util.Collections;

public class EfromAdditionSoftwareRequestPage {
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

    /* it's finding asset type element in eform Application */
    @FindBy(xpath = "//input[@formcontrolname='ipAddress']")
    WebElement ipAddress;

    /* it's finding asset number element in eform Application */
    @FindBy(xpath = "//select[@id='assetNumber']")
    WebElement assetNumber;

    @FindBy(xpath = "//select[@formcontrolname='licenseType']")
    WebElement licenseType;

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

    @FindBy(xpath = "//input[@id='radioUser1']")
    WebElement radiobtnuser;

    @FindBy(xpath = "(//input[@id='modifySoftware2'])[1]")
    WebElement radiobtncnd;

    @FindBy(xpath = "//a[normalize-space()='Terms & Conditions']")
    WebElement tndc;

    @FindBy(xpath = "//div[@id='okBtn']//input[@placeholder='Please enter details']")
    WebElement checkbox;

    @FindBy(xpath = "//button[normalize-space()='OK']")
    WebElement okbtn;

    public EfromAdditionSoftwareRequestPage(WebDriver rDriver) {
        driver = rDriver;

        PageFactory.initElements(rDriver, this);
    }


    /* This method is used to click menu and select request type */
    public void clickOnMenu() throws InterruptedException {
        CommonActions.clickingOnWebElement(menu, 2);
        CommonActions.clickingOnWebElement(type, 2);
    }

    /* This method is used to select respective project name  */
    public void selectProjectName() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.selectDropDownValue(projectname, "value", "AS_BFSI");
    }

    public void EnteringLocationDetails() throws InterruptedException {
        CommonActions.scrollDown("window.scrollBy(0, 430)");
        CommonActions.sendKeysWebElement(extensionNumber, "0000");
        CommonActions.selectDropDownValue(location, "value", "IND42-A1SLG/ZCBI");
        CommonActions.selectDropDownValue(assetNumber, "value", "Z/IND42/S/LPC");
        CommonActions.sendKeysWebElement(assetNo, "6574");
        CommonActions.sendKeysWebElement(deskNumber, "00");
    }

    public void SelectUserDetails() throws InterruptedException {
        CommonActions.selectRadioButtonValue(Collections.singletonList(radiobtnuser), "Self");
        CommonActions.sendKeysWebElement(ipAddress, "192.168.9.0");
        CommonActions.sendKeysWebElement(hostname, "PO1284784747");
    }

    public void RequirementDetails() throws InterruptedException {
        CommonActions.scrollDown("window.scrollBy(0, 430)");
        CommonActions.clickingOnWebElement(internalProject, 3);
        CommonActions.selectDropDownValue(licenseType, "index", "3");
        CommonActions.selectDropDownValue(freeware, "index", "1");
        CommonActions.sendKeysWebElement(productName, "Application");
    }

    public void acceptTndC() throws InterruptedException {
        CommonActions.clickingOnWebElement(tndc, 3);
        Thread.sleep(2000);
        CommonActions.clickingOnWebElement(checkbox, 3);
        CommonActions.clickingOnWebElement(okbtn, 3);
    }


    public void enterRemark() throws InterruptedException {
        CommonActions.sendKeysWebElement(remark, "For project purpose");
        CommonActions.scrollDown("window.scrollBy(0, 430)");
    }

    /* This method is used to click on submit button */
    public void clickOnSubmitbtn() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.clickingOnWebElement(submitbtn, 2);
    }

}
