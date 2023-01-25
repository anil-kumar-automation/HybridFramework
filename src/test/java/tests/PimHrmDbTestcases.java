package tests;


import browserFactory.DriverFactory;
import controllers.MySqlDataProvider;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import page.AddEmployeeForHrmPage;
import page.LoginForHrmPage;
import utils.CommonActions;

import java.io.IOException;

public class PimHrmDbTestcases  {
    public DriverFactory df;
    public LoginForHrmPage loginForHrm;
    public AddEmployeeForHrmPage aep;
    Logger log = Logger.getLogger(PimHrmTestcases.class);

    @Test(priority = 1)
    public void launch() throws IOException {
        df = new DriverFactory();
        df.init_driver("chrome");
        CommonActions.navigateOrangeHrmmurl();
        log.info("entering Orange HRM application URL");
    }

    @Test(priority = 2, dataProvider = "ExtractDataFromMySqlDatabase1", dataProviderClass = MySqlDataProvider.class )
    public void login(String username,String password) throws IOException, InterruptedException {
        loginForHrm = new LoginForHrmPage(DriverFactory.getDriver());
        loginForHrm.setLogindetailDb(username,password);
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

    @Test(priority = 5, dataProvider = "ExtractDataFromMySqlDatabase2", dataProviderClass = MySqlDataProvider.class)
    public void AddPageAction(String firstname,String middlename, String lastname) throws InterruptedException {
        aep = new AddEmployeeForHrmPage(DriverFactory.getDriver());
        aep.AddEmployeeActionsDb(firstname,middlename,lastname);
        log.info("Successfully entered employee in add_employee page");
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
    public void quit() {
        CommonActions.tearDown();
        log.info("closing Orange HRM application browser");
    }
}

