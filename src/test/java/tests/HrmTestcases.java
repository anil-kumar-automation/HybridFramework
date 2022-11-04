package tests;

import org.testng.annotations.*;
import pageObjects.AddEmployeePageForHrm;
import pageObjects.LoginForHrm;
import utils.CommonActions;

import java.awt.*;
import java.io.IOException;

public class HrmTestcases {
    public CommonActions ca;
    public LoginForHrm loginForHrm;
    public AddEmployeePageForHrm aep;

    @Test(priority = 1)
    public void launch() throws IOException {
        ca = new CommonActions();
        ca.launchBrowser("chrome");
        ca.navigateHrmurl();
    }

    @Test(priority = 2)
    public void login() throws IOException, InterruptedException {
        loginForHrm = new LoginForHrm();
        loginForHrm.setLogindetail();
        loginForHrm.login();
    }

    @Test(priority = 3)
    public void clickaddpage() throws IOException {
        aep = new AddEmployeePageForHrm();
        aep.clickAddEmployee();
    }

    @Test(priority = 4)
    public void addpageaction() throws IOException, AWTException, InterruptedException {
        aep = new AddEmployeePageForHrm();
        aep.AddEmployeeActions();

    }

    @Test(priority = 5)
    public void scrolling() throws InterruptedException {
        aep = new AddEmployeePageForHrm();
        Thread.sleep(4000);
        aep.scrool();
        Thread.sleep(4000);
    }

    @Test(priority = 6)
    public void dropdownaction() throws InterruptedException {
        aep = new AddEmployeePageForHrm();
        aep.dropdown();
        aep.setCalendar();
        aep.PersonalDetailsActions();
    }

    @Test(priority = 7)
    public void quit() {
        ca.tearDown();
    }
}

