package tests;


import BrowserFactory.DriverFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SnapdealScenarios;
import utils.selenium.CommonActions;

import java.io.FileInputStream;
import java.io.IOException;

import static utils.selenium.Log.info1;


public class SnapdealScenariosTest {
    public CommonActions ca;
    public DriverFactory df;
    private static Logger Log = (Logger) LogManager.getLogger(SnapdealScenariosTest.class);

    /**-----------------------------------Test Scenario TS_04----------------------------------------*/

    /**
     * TC_01, TC_02 :This method is used to navigate respective BROWSER & URL
     */
    @BeforeMethod
    @Parameters("browser")
    public void launch(String browser) throws IOException {
        ca = new CommonActions();
        df =new DriverFactory();
        df.init_driver(browser);
        ca.navigateSanpdealurl();
    }

    @DataProvider(name = "excel-data", parallel = true)
    public Object[][] excelDP() throws IOException {
        //We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
        Object[][] arrObj = getExcelData(System.getProperty("user.dir") + "/ExcelFileData/bluetoothitem.xlsx");
        return arrObj;
    }

    //This method handles the excel - opens it and reads the data from the respective cells using a for-loop & returns it in the form of a string array
    public String[][] getExcelData(String fileName) {

        String[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheetAt(0);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            Cell cell;
            data = new String[noOfRows - 1][noOfCols];

            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }
        return data;
    }

    /**TC_03 ,TC_04 ,TC_05 ,TC_06 ,TC_07 ,TC_08 ,TC_09 ,TC_10 ,TC_11 : This method is performed mentioned testcase id's */
    @Test(dataProvider = "excel-data")
    public void BluetoothItemWithEdgeBrowser(String searchName) throws Exception {
        SnapdealScenarios s = new SnapdealScenarios(DriverFactory.getDriver());
        Thread.sleep(2000);
        s.enterItemInSearch().sendKeys(searchName);
        Thread.sleep(2000);
        s.ClickSearchButton();
        info1("successfully  search the Bluetooth item");
        Log.info("successfully  search the Bluetooth item");
        ca.explicitWaitVisibilityOfElement(By.xpath("//div[@class='search-result-txt-section  marT12']/span[@style='color: #212121; font-weight: normal']"), 5);
        String actualString = s.VerifysearchCriteria().getText();
        System.out.println(actualString);
        Assert.assertTrue(actualString.contains("We've got"));
        //Reporter.log("successfully verified search result", true);
        info1("successfully verified search result");
        Log.info("successfully  search the Bluetooth item");
        s.ClickonSortby();
        Thread.sleep(2000);
        s.clickpopularity();
        info1("successfully Sorted with Popularity");
        Log.info("successfully  search the Bluetooth item");
        s.clickFristPriceRange();
        Thread.sleep(2000);
        s.clickLastPriceRange();
        Thread.sleep(2000);
        s.clickonGOButton();
        info1("successfully Selected Range 700 to 3000");
        Log.info("successfully  search the Bluetooth item");
        Thread.sleep(5000);
        s.ExcelWriter();
        info1("successfully Written the frist  Item & price  Data in excel");
        Log.info("successfully  search the Bluetooth item");
        s.clickOnFristIteam();
        ca.windowHandler();
        info1("successfully moved to another tab");
        Log.info("successfully  search the Bluetooth item");
        s.SelectAddtoCart(); // falling to see extent capability
        info1("successfully clicked the AddToCart");
        s.clickonviewCart();
        Thread.sleep(2000);
        s.clickRemoveCart();
        Thread.sleep(2000);
        String verifedremovecart = s.verifyremoveCart().getText();
        System.out.println(verifedremovecart);
        Assert.assertEquals(verifedremovecart, "Shopping Cart is empty!");
        info1("successfully verified remove cart item");
    }

    /** this is help to quit browser*/
    @AfterMethod
    public void teardown() {
        ca.tearDown();
    }
}
