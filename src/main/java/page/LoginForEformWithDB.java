package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class LoginForEformWithDB extends CommonActions {
    WebDriver driver;
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
   /* public void logIn(LoginForEformWithDB loginForEformWithDB) throws Exception {
        String choice =DBConnectionBuilder.Connections.CONNECTION_CHOICE.getValue();
        DBConnection db=new DBConnection();
        DBConnectionBuilder dbConnectionBuilder = new DBConnectionBuilder();
        if(choice.equalsIgnoreCase("Sql")){
            Connection sqlCon=dbConnectionBuilder.creatingConnection();
            db.ExtractDataFromMySQL(sqlCon);
            sendKeysWebElement(uname,loginForEformWithDB.getUsername());
            Thread.sleep(2000);
            sendKeysWebElement(Password,loginForEformWithDB.getPassword());
            clickingOnWebElement(submit, 1);
        }
        else if(choice.equalsIgnoreCase("Oracle")) {
            Connection oracleCon=dbConnectionBuilder.creatingConnection();
            db.ExtractDataFromOracle(oracleCon);
            sendKeysWebElement(uname,loginForEformWithDB.getUsername());
            Thread.sleep(2000);
            sendKeysWebElement(Password,loginForEformWithDB.getPassword());
            clickingOnWebElement(submit, 1);
        }
        else if(choice.equalsIgnoreCase("SqlServer")){
            Connection sqlServerCon = dbConnectionBuilder.creatingConnection();
            db.ExtractDataFromSqlServer(sqlServerCon);
            sendKeysWebElement(uname,loginForEformWithDB.getUsername());
            Thread.sleep(2000);
            sendKeysWebElement(Password,loginForEformWithDB.getPassword());
            clickingOnWebElement(submit, 1);
        }
        else{
            db.connectToMongoDB();
            sendKeysWebElement(uname,loginForEformWithDB.getUsername());
            Thread.sleep(2000);
            sendKeysWebElement(Password,loginForEformWithDB.getPassword());
            clickingOnWebElement(submit, 1);
        }
    }*/

    public void sendingData(String username,String password) throws InterruptedException {
        sendKeysWebElement(uname, username);
        Thread.sleep(2000);
        sendKeysWebElement(Password, password);
        clickingOnWebElement(submit, 1);
    }
    public void logIn(String username,String password) throws Exception {
        sendingData(username,password);
    }
    public void logInAndClosePopUp() {
        clickingOnWebElement(closeform, 3);
    }
}
