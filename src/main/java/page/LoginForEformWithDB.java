package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;
import utils.DBConnection;
import utils.DBConnectionBuilder;

import java.sql.Connection;

public class LoginForEformWithDB extends CommonActions {
    WebDriver driver;
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

    //Constructor to get the multiple data sets to automate.
    public LoginForEformWithDB(WebDriver rDriver) {
        driver = rDriver;

        PageFactory.initElements(rDriver, this);
    }

    /* This method is used to fill the credential from DataBase  */
    public void logIn() throws Exception {
        String choice =DBConnectionBuilder.Connections.CONNECTION_CHOICE.getValue();
        DBConnection db=new DBConnection();
        if(choice.equals("Sql")){
            Connection sqlCon=DBConnectionBuilder.creatingConnection();
            db.ExtractDataFromMySQL(sqlCon);
            sendKeysWebElement(uname,username);
            Thread.sleep(2000);
            sendKeysWebElement(Password,password);
            clickingOnWebElement(submit, 1);
        }
        else if(choice.equals("Oracle")) {
            Connection oracleCon=DBConnectionBuilder.creatingConnection();
            db.ExtractDataFromOracle(oracleCon);
            sendKeysWebElement(uname, username);
            Thread.sleep(2000);
            sendKeysWebElement(Password, password);
            clickingOnWebElement(submit, 1);
        }
        else{
            DBConnection.connectToMongoDB();
            sendKeysWebElement(uname, username);
            Thread.sleep(2000);
            sendKeysWebElement(Password, password);
            clickingOnWebElement(submit, 1);
        }
    }
    public void logInAndClosePopUp() {
        clickingOnWebElement(closeform, 3);
    }
}
