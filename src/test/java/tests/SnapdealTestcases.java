package tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageObjects.*;
import utils.CommonActions;

import java.io.FileInputStream;
import java.io.IOException;


public class SnapdealTestcases {
    public CommonActions ca;

    @BeforeTest()
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
        SearchItemData si = new SearchItemData();
        Thread.sleep(3000);
        si.enterItemInSearch().sendKeys(searchName);
        Thread.sleep(3000);
        si.ClickSearchButton();
        Reporter.log("successfully  search the Bluetooth item", true);
        VerifySearchResults vsr = new VerifySearchResults();
        ca.explicitWaitVisibilityOfElement(By.xpath("//div[@class='search-result-txt-section  marT12']/span[@style='color: #212121; font-weight: normal']"), 5);
        String actualString = vsr.VerifysearchCriteria().getText();
        System.out.println(actualString);
        Assert.assertTrue(actualString.contains("We've got"));
        Reporter.log("successfully verified search result", true);
        SortBy sb = new SortBy();
        sb.ClickonSortby();
        Thread.sleep(2000);
        sb.clickpopularity();
        Reporter.log("successfully Sorted with Popularity", true);
        SelectPriceRange spr = new SelectPriceRange();
        spr.clickFristPriceRange();
        Thread.sleep(3000);
        spr.clickLastPriceRange();
        Thread.sleep(3000);
        spr.clickonGOButton();
        Reporter.log("successfully Selected Range 700 to 3000", true);
        SaveFristItemNdPriceInExcel sfite = new SaveFristItemNdPriceInExcel();
        Thread.sleep(5000);
        sfite.ExcelWriter();
        Reporter.log("successfully Written the frist  Item & price  Data in excel", true);
        SelectFristItem sfi = new SelectFristItem();
        sfi.clickOnFristIteam();
        ca.windowHandler();
        Reporter.log("successfully moved to another tab", true);
        AddToCart atc = new AddToCart();
        atc.SelectAddtoCart();
        Reporter.log("successfully clicked the AddToCart", true);
        VerifyAddToCartItem vai = new VerifyAddToCartItem();
        vai.clickonviewCart();
        Thread.sleep(3000);
        RemoveCartAndVerify rcv = new RemoveCartAndVerify();
        rcv.clickRemoveCart();
        Thread.sleep(3000);
        String verifedremovecart = rcv.verifyremoveCart().getText();
        System.out.println(verifedremovecart);
        Assert.assertEquals(verifedremovecart, "Shopping Cart is empty!");
        Reporter.log("successfully verified remove cart item", true);
    }

    @AfterTest()
    public void teardown() {
        ca.tearDown();
    }
}
