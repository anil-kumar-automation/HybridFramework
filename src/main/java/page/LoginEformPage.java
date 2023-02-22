package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.selenium.CommonActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginEformPage {
    Logger log = Logger.getLogger(LoginEformPage.class);
    WebDriver driver;
    /* it's finding username text box element in eform Application */
    @FindBy(xpath = "//input[@id='userName']")
    WebElement uname;
    /* it's finding password text box element in eform Application */
    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement Password;
    /* it's finding Log in button element in eform Application */
    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    WebElement submit;
    /* it is used to close the pop-up in eform Application */
    @FindBy(xpath = "//div[@class='modal-footer border-top-0']/child::button[1]")
    WebElement closeform;

    @FindBy(xpath = "//tbody/tr[1]/td[7]/a[1]")
    WebElement history1;

    @FindBy(xpath = "//tbody/tr[5]/td[7]/a[1]")
    WebElement history2;

    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    WebElement okButton;

    //Constructor to get the multiple data sets to automate.
    public LoginEformPage(WebDriver rDriver) {
        driver = rDriver;

        PageFactory.initElements(rDriver, this);
    }

    /* This method is used to fill the credential from Excel  */
    public void logInThroughDataProvider(String username, String password) throws Exception {
        CommonActions.sendKeysWebElement(uname, username);
        log.info("entered username credential as : " + username);
        Thread.sleep(2000);
        CommonActions.sendKeysWebElement(Password, password);
        log.info("entered password credential as : " + password);
        CommonActions.clickingOnWebElement(submit, 1);
    }

    /* This method is used to fill the credential from property  */
    public void logIn() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        CommonActions.sendKeysWebElement(uname, prop.getProperty("username"));
        Thread.sleep(2000);
        CommonActions.sendKeysWebElement(Password, prop.getProperty("password"));
        Thread.sleep(1000);
        CommonActions.clickingOnWebElement(submit, 1);
    }

    public void scrollHistory1(){
        CommonActions.clickingOnWebElement(history1, 12);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollHistory2(){
        CommonActions.clickingOnWebElement(history2,14);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickingOnOkButton(){
        CommonActions.clickingOnWebElement(okButton,6);
    }


    public void logInAndClosePopUp() {
        CommonActions.clickingOnWebElement(closeform, 3);
    }
}