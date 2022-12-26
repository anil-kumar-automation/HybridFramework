package controllers;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;
import java.io.File;
import java.lang.reflect.Method;


public class ExcelDataProvider {

    /*it is help to read data from excel with multiple sheets*/
    @DataProvider(name = "multiSheetExcelRead", parallel = true)
    public static Object[][] multiSheetExcelRead() throws Exception {
        File file = new File("src/test/resources/TestData/document.xlsx");
        String SheetName ="Sheet2";
        System.out.println(SheetName);
        System.out.println("Opening Excel File:" + file.getAbsolutePath());
        return ExcelUtils.getTableArray(file.getAbsolutePath(), SheetName);
    }


    /*it is help to read data from excel*/
    @DataProvider(name = "singleSheetExcelRead", parallel = true)
    public static Object[][] singleSheetExcelRead() throws Exception {
        File file = new File("src/test/resources/TestData/EnterCredentialsTest.xlsx" );
        System.out.println("Opening Excel File:" + file.getAbsolutePath());
        return ExcelUtils.getTableArray(file.getAbsolutePath());
    }
}
