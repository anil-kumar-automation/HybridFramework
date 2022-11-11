package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.EformDetails;
import page.LoginForEform;
import page.ViewStatusPg;
import utils.CommonActions;

import java.io.IOException;


public class EfromViewStatusTest {

    public CommonActions ca;
    public LoginForEform loginForEform;
    public ViewStatusPg vsp;

    /*-----------------------------------Test Scenario TS_02----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        ca = new LoginForEform();
        ca.launchBrowser("chrome");
        ca.navigateEfromurl();
    }

    /*TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginForEform();
        loginForEform.logIn();
        loginForEform.logInAndClosePopUp();
    }

    /*TC_03, TC_04 :This method is click menu Hamburger  and view status option*/
    @Test(priority = 4)
    public void userClicksOnViewStatusButton() throws InterruptedException {
        vsp = new ViewStatusPg();
        vsp.Clickmenu();
        vsp.ClickViewStatusBtn();

    }

    /*TC_11 : This method is used to enter Eform number*/
    @Test(priority = 5)
    public void userFillsEFormNoAs() {
        vsp = new ViewStatusPg();
        vsp.EnterEformNO();
    }

    /*TC_11 : This method is used to submit Eform*/
    @Test(priority = 6)
    public void userClicksOnSubmitButton() throws InterruptedException {
        vsp = new ViewStatusPg();
        Thread.sleep(3000);
        vsp.ClickSubmitBtn();
    }

    /* this is help to quit browser*/
    @Test(priority = 7)
    public void QuitTest() {
        ca = new LoginForEform();
        ca.tearDown();
    }

}



