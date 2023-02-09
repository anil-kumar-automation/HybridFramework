package tests;

import BrowserFactory.DriverFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import page.AddEmployeeForHrmPage;
import page.LoginForHrmPage;
import utils.CommonActions;

import java.awt.*;
import java.io.IOException;


public class PimHrmTestcases {
    public DriverFactory df;
    public LoginForHrmPage loginForHrm;
    public AddEmployeeForHrmPage aep;

    /*public PimHrmTestcases() {
    }*/

    private static Logger Log = (Logger) LogManager.getLogger(PimHrmTestcases.class);

    //private static Logger Log = Logger.getLogger(PimHrmTestcases.class);

    public static void info(String message) {
        Log.info(message);
    }

    @Test
    public void launch() throws IOException, InterruptedException, AWTException {
        df = new DriverFactory();
        df.init_driver("edge");
        CommonActions.navigateOrangeHrmmurl();
        info("entering Orange HRM application URL");
        //log.info("entering Orange HRM application URL");
        loginForHrm = new LoginForHrmPage(DriverFactory.getDriver());
        loginForHrm.setLogindetail();
        info("entering credential on Orange HRM application");
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        Thread.sleep(3000);
        aep.clickPIM();
        info("clicking PIM On Orange HRM application");
        Thread.sleep(2000);
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.clickAddEmployee();
        info("clicking AddEmployee On Orange HRM application");
        Thread.sleep(2000);
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.AddEmployeeActions();
        info("entering Orange HRM application details as First Name,lastName, middleName and click save button");
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.AddEmployeeActions();
        info("entering Orange HRM application details as First Name,lastName, middleName and click save button");
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        Thread.sleep(3000);
        aep.scrool();
        Thread.sleep(3000);
        info("scrolled Orange HRM application ");
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.dropdown();
        info("selecting dropdowns on Orange HRM application");
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.setData();
        info("selected data on Hrm application");
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.SelectRadioButton();
        info("Select Radio Button on Hrm");
        aep.SelectCheckboxAndSave();
        info("Select Smoker checkbox on Hrm");
        Thread.sleep(2000);
        CommonActions.tearDown();
        info("closing Orange HRM application browser");
    }

  /*  @Test(priority = 2)
    public void login() throws IOException, InterruptedException {

    }

    @Test(priority = 3)
    public void clickPimAction() throws InterruptedException {

    }

    @Test(priority = 4)
    public void clickAddPageAction() throws InterruptedException {

    }


    @Test(priority = 5)
    public void AddPageAction() throws IOException, AWTException, InterruptedException {

    }

    @Test(priority = 6)
    public void scrolling() throws InterruptedException {

    }

    @Test(priority = 7)
    public void DropdownAction() throws InterruptedException {

    }

    @Test(priority = 8)
    public void SelectData() throws InterruptedException {

    }

    @Test(priority = 9)
    public void PersonalGenderSmoker() throws InterruptedException {

    }

    @Test(priority = 10)
    public void quit() {
        *//*if(result.getStatus()==result.FAILURE || result.getStatus()==result.SKIP) {
            String screenshotPath = util.captureScreenshot(driver, result.getName());
            result.setAttribute("screenshotPath", screenshotPath); //sets the value the variable/attribute screenshotPath as the path of the sceenshot
        }*//*
       *//*if(result.getStatus()==ITestResult.FAILURE)
        {
            String temp= getScreenshot(DriverFactory.getDriver());

            test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        extent.flush();
*//*

        */
    }


