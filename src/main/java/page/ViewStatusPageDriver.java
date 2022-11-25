package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonAction1;
import utils.CommonActions;

public class ViewStatusPageDriver extends CommonAction1 {
    WebDriver driver;
    /* it's finding menu element in eform Application */
    @FindBy(xpath = "//div[@class='noti__item js-item-menu']//img")
    WebElement menu;

    /* it's finding view status option element in eform Application */
    @FindBy(xpath = "//a[@id='AdminReportsComponent']")
    WebElement ViewStatusBtn;

    /* it's finding eform text box element in eform Application */
    @FindBy(css = "body > app-root > app-admin-reports > div > div > div > div > div.col-md-6.form-group > div > input")
    WebElement eform;

    /* it's finding submit button element in eform Application */
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    WebElement submitBtn;

    /* it's finding to help fine thhe text */
    @FindBy(xpath = "//div[@class='card-body']//div[1]//div[3]//b[1]")
    WebElement VerifyApprovedStatus;

    public ViewStatusPageDriver(WebDriver rDriver) {
        driver = rDriver;

        PageFactory.initElements(rDriver, this);
    }

    /* This method is used for click on menu button */
    public void Clickmenu() throws InterruptedException {
        clickingOnWebElement(menu, 3);
    }

    /* This method is used to select view status  */
    public void ClickViewStatusBtn() throws InterruptedException {
        clickingOnWebElement(ViewStatusBtn, 3);
    }

    /* This method is used for enter the eform number in text box */
    public void EnterEformNO() throws InterruptedException {
        sendKeysWebElement(eform, "435536");
    }

    /* This method is used for click submit button*/
    public void ClickSubmitBtn() {
        clickingOnWebElement(submitBtn, 3);
    }


    /* This method is used for verify the view status*/
    public void VerifyStatus() {
        String verifyViewStatus = VerifyApprovedStatus.getText();
        System.out.println(verifyViewStatus);
        if (verifyViewStatus.equals("Approved on")) {
            System.out.println("Eform status :" + "EForm request is Approved");
        } else {
            System.out.println("Eform status :" + "EForm request is not yet Approved");
        }
    }
}
