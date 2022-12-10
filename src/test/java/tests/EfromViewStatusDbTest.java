package tests;

import BrowserFactory.DriverFactory;
import controllers.MySqlDataProvider;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginForEformWithDB;
import page.ViewStatusPage;
import utils.CommonActions;

import java.io.IOException;

public class EfromViewStatusDbTest {
    Logger log = Logger.getLogger(EfromViewStatusDbTest.class);
    public DriverFactory df;
    public ViewStatusPage vsp;
    public LoginForEformWithDB lb;
    /*-----------------------------------Test Scenario TS_02----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @BeforeMethod
    public void launchBrowserTest() throws IOException {
        df = new DriverFactory();
        df.init_driver("chrome");
        CommonActions.navigateEformmurl();
        log.info("entering eform application URL");
    }
    /*TC_03 :This method is used to login using credential*/
    @Test(priority = 1,dataProvider = "ExtractDataFromMySqlDatabase", dataProviderClass = MySqlDataProvider.class)
    public void EnterCredentialsTestWithDb(String username,String password) throws Exception {
        System.out.println(username);
        System.out.println(password);
        lb = new LoginForEformWithDB(DriverFactory.getDriver());
        lb.logIn(username,password);
        log.info("successfully entered credentials");
        lb.logInAndClosePopUp();
    }
    /*TC_03, TC_04 :This method is click menu Hamburger  and view status option*/
    @Test(priority = 2)
    public void userClicksOnViewStatusButton() throws InterruptedException {
        vsp = new ViewStatusPage(DriverFactory.getDriver());
        vsp.Clickmenu();
        log.info("successfully clicked Menu icon");
        vsp.ClickViewStatusBtn();
        log.info("successfully clicked view status button");
    }
    /*TC_11 : This method is used to enter Eform number*/
    @Test(priority = 3)
    public void userFillsEFormNoAs() throws InterruptedException {
        vsp = new ViewStatusPage(DriverFactory.getDriver());
        vsp.EnterEformNO();
        log.info("successfully entered eform number");
    }
    /*TC_11 : This method is used to submit Eform*/
    @Test(priority = 4)
    public void userClicksOnSubmitButton() throws InterruptedException {
        vsp = new ViewStatusPage(DriverFactory.getDriver());
        vsp.ClickSubmitBtn();
        log.info("successfully clicked submit button on view status page");
    }
    /* this is help to quit browser*/
    @AfterMethod
    public void QuitTest() {
        CommonActions.tearDown();
        log.info("Browser is closed");
    }
}



