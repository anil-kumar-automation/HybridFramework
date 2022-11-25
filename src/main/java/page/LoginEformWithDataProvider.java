package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class LoginEformWithDataProvider extends CommonActions {

    /* it's finding username text box element in eform Application */
    @FindBy(xpath = "//input[@id='userName']")
    WebElement uname;
    /* it's finding password text box element in eform Application */
    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement Password;
    /* it's finding Log in button element in eform Application */
    @FindBy(xpath = "//button[normalize-space()='Sign in']")
    WebElement submit;
    /* it is used to close the pop-up in eform Application */
    @FindBy(xpath = "//div[@class='modal-footer border-top-0']/child::button[1]")
    WebElement closeform;




    //Constructor to get the multiple data sets to automate.
    public LoginEformWithDataProvider() {
        PageFactory.initElements(driver, this);
    }

    /* This method is used to fill the credential from DataBase  */
    public void logInThroughDataProvider(String username, String password) throws Exception {
        sendKeysWebElement(uname, username);
        Thread.sleep(2000);
        sendKeysWebElement(Password, password);
        clickingOnWebElement(submit, 1);

    }

}