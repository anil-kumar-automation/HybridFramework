package tests;

import BrowserFactory.DriverFactory;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import page.EfromAdditionSoftwareRequestPage;
import page.EfromAdditionSoftwareRequestPageJson;
import page.LoginEformPage;
import utils.CommonActions;

import java.io.IOException;

public class EfromAdditionSoftwareRequestTestJson {


    public DriverFactory df;
    public LoginEformPage loginForEform;
    public EfromAdditionSoftwareRequestPageJson easr;
    Logger log = Logger.getLogger(EfromAdditionSoftwareRequestTestJson.class);


    /**-----------------------------------Test Scenario TS_01----------------------------------------*/

    /** TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        log.info("****************************** Starting test cases execution  *****************************************");
        df = new DriverFactory();
        df.init_driver("chrome");
        CommonActions.navigateEformmurl();
        log.info("entering eform application URL");
    }

    /** TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logIn();
        log.info("successfully entered credentials");
        loginForEform.logInAndClosePopUp();
    }

    /** TC_04 :This method is used to login using credential*/
    @Test(priority = 3)
    public void userOnAdditionalSoftwareTest() throws InterruptedException, IOException, ParseException {
        easr = new EfromAdditionSoftwareRequestPageJson(DriverFactory.getDriver());
        easr.clickOnMenu();
        log.info("successfully clicked Menu icon");
    }

    /** TC_05,TC_06,TC_07,TC_08 :This method is used perform mentioned testcase id's*/
    @Test(priority = 4)
    public void EnterRequireDetailsTest() throws InterruptedException, IOException, ParseException {
        easr = new EfromAdditionSoftwareRequestPageJson(DriverFactory.getDriver());
        easr.enterRequireDetails();
        log.info("entered requirement details on AdditionSoftwareRequest page");
    }

    /** this method is used to click acceptance checkbox */
    @Test(priority = 5)
    public void ClickacceptTndCTest() throws InterruptedException {
        easr = new EfromAdditionSoftwareRequestPageJson(DriverFactory.getDriver());
        easr.acceptTndC();
        log.info("successfully checked accepted checkbox on AdditionSoftwareRequest page");
    }

    /** TC_10 :This method is used to login using credential*/
    @Test(priority = 6)
    public void enterRemarkTest() throws InterruptedException, IOException, ParseException {
        easr = new EfromAdditionSoftwareRequestPageJson(DriverFactory.getDriver());
        easr.enterRemark();
        log.info("successfully typed remark description on AdditionSoftwareRequest page");
    }

    /** TC_011 :This method is used to submit details*/
    @Test(priority = 7)
    public void ClickSubmitTest() throws InterruptedException {
        easr = new EfromAdditionSoftwareRequestPageJson(DriverFactory.getDriver());
        easr.clickOnSubmitbtn();
        log.info("successfully clicked submit button on AdditionSoftwareRequest page");
    }


    /** it's help to quit the browsers*/
    @Test(priority = 8)
    public void QuitTest() {
        CommonActions.tearDown();
        log.info("Browser is closed");
    }

}
