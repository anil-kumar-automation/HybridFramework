package tests;

import controllers.ExcelDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginEformWithDataProvider;
import utils.CommonActions;

import java.io.IOException;


public class EfromLoginMultipleSetOfDataTest {

    public CommonActions ca;

    public LoginEformWithDataProvider loginForEformDp;

    /*-----------------------------------Test Scenario TS_01----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @BeforeMethod
    public void launchBrowserTest() throws IOException {
        ca = new CommonActions();
        ca.launchBrowser("chrome");
        ca.navigateEfromurl();
    }

    /* TC_03 :This method is used to login using credential*/
    @Test(dataProvider = "singleSheetExcelRead", dataProviderClass = ExcelDataProvider.class)
    public void EnteOnCredentialsTest(String username, String password) throws Exception {
        loginForEformDp = new LoginEformWithDataProvider();
        loginForEformDp.logInThroughDataProvider(username, password);
    }

    /* it's help to quit the browsers*/
    @AfterMethod
    public void QuitTest() {
        ca = new CommonActions();
        ca.tearDown();
    }

}

