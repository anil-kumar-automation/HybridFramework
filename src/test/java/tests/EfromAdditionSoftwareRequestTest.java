package tests;

import BrowserFactory.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import page.EfromAdditionSoftwareRequestPage;
import page.LoginEformPage;
import utils.CommonActions;

import java.io.IOException;

public class EfromAdditionSoftwareRequestTest {


    public DriverFactory df;
    public LoginEformPage loginForEform;
    public EfromAdditionSoftwareRequestPage easr;
    Logger log = Logger.getLogger(EfromAdditionSoftwareRequestTest.class);


    /*-----------------------------------Test Scenario TS_01----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        log.info("****************************** Starting test cases execution  *****************************************");
        df = new DriverFactory();
        df.init_driver("chrome");
        CommonActions.navigateEformmurl();
        log.info("entering eform application URL");
        log.warn("Hey this just a warning message");
        log.fatal("hey this is just fatal error message");
        //log.debug("this is debug message");
    }

    /*TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logIn();
        log.info("successfully entered credentials");
        loginForEform.logInAndClosePopUp();
    }


    @Test(priority = 3)
    public void userOnAdditionalSoftwareTest() throws InterruptedException {
        easr = new EfromAdditionSoftwareRequestPage(DriverFactory.getDriver());
        easr.clickOnMenu();
        log.info("successfully clicked Menu icon");
        easr.selectProjectName();
        log.info("successfully selected project name on AdditionSoftwareRequest page");
        easr.EnteringLocationDetails();
        log.info("successfully filled location on AdditionSoftwareRequest page");
        easr.SelectUserDetails();
        log.info("selected user details on AdditionSoftwareRequest page");
        easr.RequirementDetails();
        log.info("entered requirement details on AdditionSoftwareRequest page");
        easr.acceptTndC();
        log.info("successfully checked accepted checkbox on AdditionSoftwareRequest page");
        easr.enterRemark();
        log.info("successfully typed remark description on AdditionSoftwareRequest page");
    }


    @Test(priority = 4)
    public void ClickSubmitTest() throws InterruptedException {
        easr = new EfromAdditionSoftwareRequestPage(DriverFactory.getDriver());
        easr.clickOnSubmitbtn();
        log.info("successfully clicked submit button on AdditionSoftwareRequest page");
    }


    /* it's help to quit the browsers*/
    @Test(priority = 5)
    public void QuitTest() {
        CommonActions.tearDown();
        log.info("Browser is closed");
    }

}
