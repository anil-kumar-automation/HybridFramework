package pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginForHrm extends CommonActions {
    @FindBy(css = "input[placeholder='Username']")
    WebElement uname;
    @FindBy(css = "input[placeholder='Password']")
    WebElement pword;
    @FindBy(css = "button[type='submit']")
    WebElement LogIn_Button;


    public LoginForHrm() {
        PageFactory.initElements(driver, this);
    }


    //through propertiry files
    public void  setLogindetail() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.Properties");
        Properties prop = new Properties();
        prop.load(fis);
        sendKeysWebElement(uname,prop.getProperty("username"));
        Thread.sleep(2000);
        sendKeysWebElement(pword,prop.getProperty("password"));
    }


    public void login() {
        clickingOnWebElement(LogIn_Button, 5);

    }
}


