package tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;
import utils.CommonActions;

import java.io.FileInputStream;
import java.io.IOException;


public class SnapdealScenariosTest {
    public CommonActions ca;

    @BeforeMethod
    public void launch() throws IOException {
        ca = new CommonActions();
        ca.launchBrowser("edge");
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


    @Test(dataProvider = "excel-data")
    public void BluetoothItemWithEdgeBrowser(String searchName) throws Exception {
        SnapdealScenarios s = new SnapdealScenarios();
        Thread.sleep(2000);
        s.enterItemInSearch().sendKeys(searchName);
        Thread.sleep(2000);
        s.ClickSearchButton();
        Reporter.log("successfully  search the Bluetooth item", true);
        ca.explicitWaitVisibilityOfElement(By.xpath("//div[@class='search-result-txt-section  marT12']/span[@style='color: #212121; font-weight: normal']"), 5);
        String actualString = s.VerifysearchCriteria().getText();
        System.out.println(actualString);
        Assert.assertTrue(actualString.contains("We've got"));
        Reporter.log("successfully verified search result", true);
        s.ClickonSortby();
        Thread.sleep(2000);
        s.clickpopularity();
        Reporter.log("successfully Sorted with Popularity", true);
        s.clickFristPriceRange();
        Thread.sleep(2000);
        s.clickLastPriceRange();
        Thread.sleep(2000);
        s.clickonGOButton();
        Reporter.log("successfully Selected Range 700 to 3000", true);
        Thread.sleep(5000);
        s.ExcelWriter();
        Reporter.log("successfully Written the frist  Item & price  Data in excel", true);
        s.clickOnFristIteam();
        ca.windowHandler();
        Reporter.log("successfully moved to another tab", true);
        s.SelectAddtoCart();
        Reporter.log("successfully clicked the AddToCart", true);
        s.clickonviewCart();
        Thread.sleep(2000);
        s.clickRemoveCart();
        Thread.sleep(2000);
        String verifedremovecart = s.verifyremoveCart().getText();
        System.out.println(verifedremovecart);
        Assert.assertEquals(verifedremovecart, "Shopping Cart is empty!");
        Reporter.log("successfully verified remove cart item", true);
    }

    @AfterMethod
    public void teardown() {
        ca.tearDown();
    }
}
