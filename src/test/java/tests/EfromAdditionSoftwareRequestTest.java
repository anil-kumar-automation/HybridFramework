package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.EformAdditionSoftwareRequestPage;
import page.EformPrivilegeRequestPage;
import page.LoginForEform;
import page.ViewStatusPage;
import utils.CommonActions;

import java.io.IOException;

public class EfromAdditionSoftwareRequestTest {
    public CommonActions ca;
    public LoginForEform loginForEform;
    public EformAdditionSoftwareRequestPage easr;


    /*-----------------------------------Test Scenario TS_01----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        ca = new CommonActions();
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


    @Test(priority = 3)
    public void userOnAdditionalSoftwareTest() throws InterruptedException {
        easr = new EformAdditionSoftwareRequestPage();
        easr.clickOnMenu();
        easr.selectProjectName();
        easr.EnteringLocationDetails();
        easr.SelectUserDetails();
        easr.RequirementDetails();
        easr.acceptTndC();
        easr.enterRemark();
    }


    @Test(priority = 4)
    public void ClickSubmitTest() throws InterruptedException {
        easr = new EformAdditionSoftwareRequestPage();
        easr.clickOnSubmitbtn();
    }


    /* it's help to quit the browsers*/
    @Test(priority = 5)
    public void QuitTest() {
        ca = new CommonActions();
        ca.tearDown();
    }
}
