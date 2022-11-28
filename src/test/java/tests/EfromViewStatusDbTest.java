package tests;

import BrowserFactory.DriverFactory;
import org.testng.annotations.*;
import page.LoginEformPage;
import page.ViewStatusPage;
import utils.CommonActions;

import java.io.IOException;
import java.sql.SQLException;


public class EfromViewStatusDbTest {

    public DriverFactory df;
    public CommonActions ca;
    public LoginEformPage loginForEform;
    public ViewStatusPage vsp;
    /*-----------------------------------Test Scenario TS_02----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        df = new DriverFactory();
        DriverFactory df = new DriverFactory();
        df.init_driver("chrome");
    }

   /*TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logIn();
        loginForEform.logInAndClosePopUp();
    }

    /* TC_03 :This method is used to login using credential *//*
    @Test(priority = 2)
    public void EnterCredentialsTestWithDB(String username, String password) throws InterruptedException, IOException, SQLException {

        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logInDb(username, password);
        loginForEform.logInAndClosePopUp();
    }*/

    /*TC_03, TC_04 :This method is click menu Hamburger  and view status option*/
    @Test(priority = 3)
    public void userClicksOnViewStatusButton() throws InterruptedException {
        vsp = new ViewStatusPage(DriverFactory.getDriver());
        vsp.Clickmenu();
        vsp.ClickViewStatusBtn();

    }

    /*TC_11 : This method is used to enter Eform number*/
    @Test(priority = 4)
    public void userFillsEFormNoAs() throws InterruptedException {
        vsp = new ViewStatusPage(DriverFactory.getDriver());
        vsp.EnterEformNO();
    }

    /*TC_11 : This method is used to submit Eform*/
    @Test(priority = 5)
    public void userClicksOnSubmitButton() throws InterruptedException {
        vsp = new ViewStatusPage(DriverFactory.getDriver());
        vsp.ClickSubmitBtn();
    }

    /* this is help to quit browser*/
    @Test(priority = 6)
    public void QuitTest() {
        ca = new CommonActions();
        ca.tearDown();
    }

}



