package page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.selenium.CommonActions;

public class EformPrivilegeRequestPage   {

    WebDriver driver;
    /* it's finding menu element in eform Application */
    @FindBy(xpath = "//div[@class='noti__item js-item-menu']//img")
    WebElement menu;

    /* it's finding request type element in eform Application */
    @FindBy(xpath = "//a[@id='PrivilegeRequestFormComponent']")
    WebElement type;

    /* it's finding project name element in eform Application */
    @FindBy(xpath = "//select[@id='projectName']")
    WebElement projectname;

    /* it's finding asset type element in eform Application */
    @FindBy(xpath = "//select[@id='assetType']")
    WebElement assetType;

    /* it's finding asset number element in eform Application */
    @FindBy(xpath = "//select[@id='assetNumber']")
    WebElement assetNumber;

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
    @FindBy(xpath = "//input[@id='revokeDate']")
    WebElement revokeDate;

    /* it's finding hostname element in eform Application */
    @FindBy(xpath = "//input[@id='hostName']")
    WebElement hostname;

    /* it's finding remark element in eform Application */
    @FindBy(xpath = "//textarea[@id='remarks']")
    WebElement remark;

    /* it's finding checkbox element in eform Application */
    @FindBy(xpath = "//input[@id='iAccept']")
    WebElement checkbox;

    /* it's finding submit button element in eform Application */
    @FindBy(xpath = "//button[normalize-space()='Submit eForm']")
    WebElement submitbtn;

    /*initializing the page objects*/
    public EformPrivilegeRequestPage(WebDriver rDriver) {
        driver = rDriver;

        PageFactory.initElements(rDriver, this);
    }

    /* This method is used to click menu and select request type */
    public void clickOnMenu() {
        CommonActions.clickingOnWebElement(menu, 2);
        CommonActions.clickingOnWebElement(type, 2);
    }

    /* This method is used to select respective project name  */
    public void selectProjectName() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.selectDropDownValue(projectname, "value", "AS_BFSI");
    }


    /* This method is used to fill location details */
    public void enterLocationDetails() throws InterruptedException {
        CommonActions.selectDropDownValue(assetType, "value", "Desktop");
        CommonActions.selectDropDownValue(assetNumber, "value", "Z/IND42/S/LPC");
        CommonActions.sendKeysWebElement(assetNo, "6574");
        CommonActions.sendKeysWebElement(extensionNumber, "0000");
        CommonActions.scrollDown("window.scrollBy(0, 430);");
        Thread.sleep(1000);
        CommonActions.selectDropDownValue(location, "value", "IND42-A1SLG/ZCBI");
        Thread.sleep(1000);
        CommonActions.sendKeysWebElement(deskNumber, "00");
    }

    /* This method is used to host detail */
    public void hostName() throws InterruptedException {
        hostname.sendKeys("PO1284784747");
    }

    /* This method is used to enter remark details */
    public void enterRemark() throws InterruptedException {
        Thread.sleep(1000);
        CommonActions.sendKeysWebElement(remark, "For project purpose");
        Thread.sleep(1000);
        CommonActions.scrollDown("window.scrollBy(0, 400);");
    }

    /* This method is used to accept the terms and conditions before submitting request */
    public void clickOnCheckBox() {
        CommonActions.clickingOnWebElement(checkbox, 2);
    }

    /* This method is used to click on submit button */
    public void clickOnSubmitbtn() {
        CommonActions.clickingOnWebElement(submitbtn, 2);
    }

}
