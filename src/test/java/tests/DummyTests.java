package tests;

import BrowserFactory.DriverFactory;
import org.testng.annotations.Test;
import utils.pdf_docx_utils.CombinedPdf;
import utils.pdf_docx_utils.PageFormatUtils;
import utils.selenium.CommonActions;

import java.io.IOException;

public class DummyTests {

    public DriverFactory df;

    @Test(priority = 1)
    public void launchBrowserTest() throws IOException {
        df = new DriverFactory();
        df.init_driver("chrome");
        DriverFactory.getDriver().get("https://the-internet.herokuapp.com/");
    }
    @Test(priority = 2)
    public void printAndSaveDocumentTestForHistory1() throws Exception {
        String filePath = "C:\\Users\\SK66921\\Documents\\Git\\HybridFramework\\content1.pdf";
        PageFormatUtils.printAndSave(filePath);
        Thread.sleep(5000);
    }
    @Test(priority = 3)
    public void printAndSaveDocumentTestForHistory2() throws Exception {
        String filePath = "C:\\Users\\SK66921\\Documents\\Git\\HybridFramework\\content2.pdf";
        PageFormatUtils.printAndSave(filePath);
        Thread.sleep(2000);
    }
    @Test(priority = 4)
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
    @Test(priority = 5)
    public void QuitTest() {
        CommonActions.tearDown();
    }

}
