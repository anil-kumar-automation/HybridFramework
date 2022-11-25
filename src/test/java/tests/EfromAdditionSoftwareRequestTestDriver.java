package tests;

import factory.DriverFactory;
import org.testng.annotations.Test;
import page.EformAdditionSoftwareRequestPage;
import page.EformAdditionSoftwareRequestPageDriver;
import page.LoginForEform;
import page.LoginForEformDriver;
import utils.CommonAction1;
import utils.CommonActions;

import java.io.IOException;

public class EfromAdditionSoftwareRequestTestDriver {
    public DriverFactory df;
    public CommonAction1 ca;
    public LoginForEformDriver loginForEform;
    public EformAdditionSoftwareRequestPageDriver easr;


    /*-----------------------------------Test Scenario TS_01----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        df = new DriverFactory();
        df.init_driver("chrome");
    }

    /*TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginForEformDriver(DriverFactory.getDriver());
        loginForEform.logIn();
        loginForEform.logInAndClosePopUp();
    }


    @Test(priority = 3)
    public void userOnAdditionalSoftwareTest() throws InterruptedException {
        easr = new EformAdditionSoftwareRequestPageDriver(DriverFactory.getDriver());
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
        easr = new EformAdditionSoftwareRequestPageDriver(DriverFactory.getDriver());
        easr.clickOnSubmitbtn();
    }


    /* it's help to quit the browsers*/
    @Test(priority = 5)
    public void QuitTest() {
        DriverFactory.getDriver().quit();
    }
}
