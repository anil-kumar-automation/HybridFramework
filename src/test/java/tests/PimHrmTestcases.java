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


    private static Logger Log = (Logger) LogManager.getLogger(PimHrmTestcases.class);
    //private static Logger Log = Logger.getLogger(PimHrmTestcases.class);

    public static void info(String message) {
        Log.info(message);
    }

    @Test(priority = 1)
    public void launch() throws IOException {
        df = new DriverFactory();
        df.init_driver("edge");
        CommonActions.navigateOrangeHrmmurl();
        info("entering Orange HRM application URL");

    }

    @Test(priority = 2)
    public void login() throws IOException, InterruptedException {
        loginForHrm = new LoginForHrmPage(DriverFactory.getDriver());
        loginForHrm.setLogindetail();
        info("entering credential on Orange HRM application");
    }

    @Test(priority = 3)
    public void clickPimAction() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        Thread.sleep(3000);
        aep.clickPIM();
        info("clicking PIM On Orange HRM application");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void clickAddPageAction() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.clickAddEmployee();
        info("clicking AddEmployee On Orange HRM application");
        Thread.sleep(2000);
    }


    @Test(priority = 5)
    public void AddPageAction() throws IOException, AWTException, InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.AddEmployeeActions();
        info("entering Orange HRM application details as First Name,lastName, middleName and click save button");
    }

    @Test(priority = 6)
    public void scrolling() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        Thread.sleep(3000);
        aep.scrool();
        Thread.sleep(3000);
        info("scrolled Orange HRM application ");
    }

    @Test(priority = 7)
    public void DropdownAction() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.dropdown();
        info("selecting dropdowns on Orange HRM application");
    }

    @Test(priority = 8)
    public void SelectData() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.setData();
        info("selected data on Hrm application");
    }

    @Test(priority = 9)
    public void PersonalGenderSmoker() throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.SelectRadioButton();
        info("Select Radio Button on Hrm");
        aep.SelectCheckboxAndSave();
        info("Select Smoker checkbox on Hrm");
        Thread.sleep(2000);
    }

    @Test(priority = 10)
    public void quit() {
        CommonActions.tearDown();
        info("closing Orange HRM application browser");
    }
}

