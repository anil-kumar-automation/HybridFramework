/*
package tests;

import BrowserFactory.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import listeners.ExtentTestNGIReporterListener;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.AddEmployeeForHrmPage;
import page.EfromAdditionSoftwareRequestPage;
import page.LoginEformPage;
import page.LoginForHrmPage;
import utils.CommonActions;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PimHrmReportTestcases {

    public DriverFactory df;
    public LoginForHrmPage loginForHrm;
    public AddEmployeeForHrmPage aep;
    */
/* Logger log = Logger.getLogger(PimHrmTestcases.class);*//*

    private Logger log = (Logger) LogManager.getLogger(PimHrmReportTestcases.class);
    ExtentReports extent = new ExtentReports();
    ExtentTest extentTest;
    public ExtentTestNGIReporterListener etrl;

    public static String getScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";

        File destination = new File(path);

        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }

        return path;
    }

    @BeforeMethod
    public void setup() {
        etrl = new ExtentTestNGIReporterListener();
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReportWithScreenShot.html");

        extent = new ExtentReports();

        extent.attachReporter(reporter);

        extentTest = extent.createTest("LoginTest");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            String temp = getScreenshot(DriverFactory.getDriver());

            extentTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }

        extent.flush();

    }

    @Test(priority = 1)
    public void launch() throws IOException {
        df = new DriverFactory();
        df.init_driver("edge");
        CommonActions.navigateOrangeHrmmurl();
        log.info("entering Orange HRM application URL");
    }

    @Test(priority = 2)
    public void login() throws IOException, InterruptedException {
        loginForHrm = new LoginForHrmPage(DriverFactory.getDriver());
        loginForHrm.setLogindetail();
        log.info("entering credential on Orange HRM application");
    }

    @Test(priority = 3)
    public void clickPimAction() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        Thread.sleep(3000);
        aep.clickPIM();
        log.info("clicking PIM On Orange HRM application");
        Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void clickAddPageAction() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.clickAddEmployee();
        log.info("clicking AddEmployee On Orange HRM application");
        Thread.sleep(2000);
    }


    @Test(priority = 5)
    public void AddPageAction() throws IOException, AWTException, InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.AddEmployeeActions();
        log.info("entering Orange HRM application URL");
    }

    @Test(priority = 6)
    public void scrolling() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        Thread.sleep(3000);
        aep.scrool();
        Thread.sleep(3000);
        log.info("scrolled Orange HRM application ");
    }

    @Test(priority = 7)
    public void DropdownAction() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.dropdown();
        log.info("selecting dropdowns on Orange HRM application");
    }

    @Test(priority = 8)
    public void SelectData() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.setData();
        log.info("selected data on Hrm application");
    }

    @Test(priority = 9)
    public void PersonalGenderSmoker() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.SelectRadioButton();
        log.info("Select Radio Button on Hrm");
        aep.SelectCheckboxAndSave();
        log.info("Select Smoker checkbox on Hrm");
        Thread.sleep(2000);
    }

   @Test(priority = 10)
    public void quit() throws IOException {
        */
/*if(result.getStatus()==result.FAILURE || result.getStatus()==result.SKIP) {
            String screenshotPath = util.captureScreenshot(driver, result.getName());
            result.setAttribute("screenshotPath", screenshotPath); //sets the value the variable/attribute screenshotPath as the path of the sceenshot
        }*//*

       */
/*if(result.getStatus()==ITestResult.FAILURE)
        {
            String temp= getScreenshot(DriverFactory.getDriver());

            test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        extent.flush();
*//*

        CommonActions.tearDown();
        log.info("closing Orange HRM application browser");
    }
}

*/
