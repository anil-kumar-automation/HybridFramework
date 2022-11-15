package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;
import utils.DBConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class LoginForEformWithDB extends CommonActions {
    String username;
    String password;

    //Constructor to get the multiple data sets to automate.
    public LoginForEformWithDB(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public LoginForEformWithDB() {
        PageFactory.initElements(driver, this);
    }

    /* This method is used to fill the credential from DataBase  */
    public void logIn() throws IOException, InterruptedException, SQLException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.Properties");
        Properties prop = new Properties();
        prop.load(fis);
        String choice=prop.getProperty("connectionChoice");
        DBConnection db = new DBConnection();
        if(choice.equals("Sql")){
            Connection sqlCon = db.creatingConnection();
            db.ExtractDataFromMySQL(sqlCon);
            sendKeysWebElement(uname,username);
            Thread.sleep(2000);
            sendKeysWebElement(Password,password);
            clickingOnWebElement(submit, 1);
        }
        else if(choice.equals("Oracle")){
            Connection oracleCon = db.connectToOracle();
            db.ExtractDataFromOracle(oracleCon);
            sendKeysWebElement(uname,username);
            Thread.sleep(2000);
            sendKeysWebElement(Password,password);
            clickingOnWebElement(submit, 1);
        }
    }

    public void logInAndClosePopUp() {
        clickingOnWebElement(closeform, 3);
    }

}