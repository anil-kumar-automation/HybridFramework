package tests;

import BrowserFactory.DriverFactory;
import org.testng.annotations.Test;
import page.EfromAdditionSoftwareRequestPage;
import page.LoginEformPage;
import utils.CommonActions;

import java.io.IOException;

public class EfromAdditionSoftwareRequestTest {
    public CommonActions ca;
    public DriverFactory df;
    public LoginEformPage loginForEform;
    public EfromAdditionSoftwareRequestPage easr;


    /*-----------------------------------Test Scenario TS_01----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        DriverFactory df = new DriverFactory();
        ca = new CommonActions();
        df.init_driver("chrome");
        ca.navigateEfromurl();
    }

    /*TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logIn();
        loginForEform.logInAndClosePopUp();
    }


    @Test(priority = 3)
    public void userOnAdditionalSoftwareTest() throws InterruptedException {
        easr = new EfromAdditionSoftwareRequestPage(DriverFactory.getDriver());
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
        easr = new EfromAdditionSoftwareRequestPage(DriverFactory.getDriver());
        easr.clickOnSubmitbtn();
    }


    /* it's help to quit the browsers*/
    @Test(priority = 5)
    public void QuitTest() {
        ca = new CommonActions();
        ca.tearDown();
    }

}
