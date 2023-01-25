package tests;

import browserFactory.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import page.EformPrivilegeRequestPage;
import page.LoginEformPage;
import utils.CommonActions;

import java.io.IOException;


public class EfromPrivilegeRequestTest {
    Logger log = Logger.getLogger(EfromPrivilegeRequestTest.class);
    public DriverFactory df;
    public LoginEformPage loginForEform;
    public EformPrivilegeRequestPage ef;


    /**-----------------------------------Test Scenario TS_01----------------------------------------*/

    /** TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        df = new DriverFactory();
        df.init_driver("chrome");
        CommonActions.navigateEformmurl();
        log.info("entering eform application URL");
    }

    /**TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logIn();
        log.info("successfully entered credentials");
        loginForEform.logInAndClosePopUp();
    }

    /** TC_04 , TC_05 :Select Privilege request */
    @Test(priority = 3)
    public void userIsOnPrivilegeRequestPageTest() {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.clickOnMenu();
        log.info("successfully clicked Menu icon");
    }

    /**TC_05 : This method is used to select project name */
    @Test(priority = 4)
    public void userSelectProjectTest() throws InterruptedException {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.selectProjectName();
        log.info("successfully selected project name on PrivilegeRequest page");
    }

    /**TC_06 ,TC_07 : This method is used to fill required information of Location */
    @Test(priority = 5)
    public void userFillsLocationDetailsTest() throws InterruptedException {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.enterLocationDetails();
        log.info("successfully entered location details on PrivilegeRequest page");
    }

    /**TC_08 : This method is used to fill host details */
    @Test(priority = 6)
    public void userFillsRequirementDetailsTest() throws InterruptedException {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.hostName();
        log.info("successfully entered hostname on PrivilegeRequest page");
    }

    /**TC_09 : This method is used to for remark */
    @Test(priority = 7)
    public void userFillsRemarksTest() throws InterruptedException {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.enterRemark();
        log.info("successfully entered remarks on PrivilegeRequest page");
    }

    /**TC_10 : This method is used for check box to accpet terms */
    @Test(priority = 8)
    public void userClickOnCheckboxTest() {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.clickOnCheckBox();
        log.info("successfully checked accepted checkbox on PrivilegeRequest page");
    }

    /**TC_11 : This method is used to submit E-form*/
    @Test(priority = 9)
    public void userClickOnSubmitButtonTest() {
        ef = new EformPrivilegeRequestPage(DriverFactory.getDriver());
        ef.clickOnSubmitbtn();
        log.info("successfully clicked submit button on PrivilegeRequest page");
    }

    /** it's help to quit the browsers*/
    @Test(priority = 10)
    public void QuitTest() {
        CommonActions.tearDown();
        log.info("Browser is closed");
    }

}

