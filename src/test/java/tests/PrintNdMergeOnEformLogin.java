package tests;

import BrowserFactory.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import page.LoginEformPage;
import utils.pdf_docx_utils.CombinedPdf;
import utils.pdf_docx_utils.PageFormatUtils;
import utils.selenium.CommonActions;
import java.io.IOException;

public class PrintNdMergeOnEformLogin {

    public DriverFactory df;
    public LoginEformPage loginForEform;
    Logger log = Logger.getLogger(PrintNdMergeOnEformLogin.class);

    /*-----------------------------------Test Scenario TS_01----------------------------------------*/

    /* TC_02 :This method is used to navigate respective BROWSER & URL*/
    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        log.info("****************************** Starting test cases execution  *****************************************");
        df = new DriverFactory();
        df.init_driver("chrome");
        CommonActions.navigateEformmurl();
        log.info("entering eform application URL");
    }

    /*TC_03 :This method is used to login using credential*/
    @Test(priority = 2)
    public void EnterCredentialsTest() throws InterruptedException, IOException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        loginForEform.logIn();
        log.info("successfully entered credentials");
        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void scroll() throws InterruptedException {
        Thread.sleep(5000);
        CommonActions.scrollDown("window.scrollBy(0, 700);");
    }

    @Test(priority = 4)
    public void actionsForHistory1() throws InterruptedException {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        Thread.sleep(4000);
        loginForEform.scrollHistory1();
        Thread.sleep(4000);
    }

    @Test(priority = 5)
    public void printAndSaveDocumentTestForHistory1() throws Exception {
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        String filePath = "C:\\Users\\as61837\\Documents\\GitHub\\HybridFramework\\content1.pdf";
        PageFormatUtils.printAndSave(filePath);
        Thread.sleep(5000);
        loginForEform.clickingOnOkButton();
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void actionsForHistory2() throws InterruptedException {
        Thread.sleep(5000);
        loginForEform = new LoginEformPage(DriverFactory.getDriver());
        Thread.sleep(4000);
        loginForEform.scrollHistory2();
        Thread.sleep(4000);
    }

    @Test(priority = 7)
    public void printAndSaveDocumentTestForHistory2() throws Exception {
        String filePath = "C:\\Users\\as61837\\Documents\\GitHub\\HybridFramework\\content2.pdf";
        PageFormatUtils.printAndSave(filePath);
        Thread.sleep(2000);
    }

    @Test(priority = 8)
    public void testCreateDocx() throws IOException {
        String pdf1 = "content1.pdf";
        String pdf2 = "content2.pdf";
        String targetPdf = "merged.pdf";
        CombinedPdf.mergedPdf(pdf1,pdf2,targetPdf);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* it's help to quit the browsers*/
    @Test(priority = 9)
    public void QuitTest() {
        CommonActions.tearDown();
        log.info("Browser is closed");
    }
}





