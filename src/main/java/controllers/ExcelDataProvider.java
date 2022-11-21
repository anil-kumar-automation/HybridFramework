package controllers;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;


import java.io.File;


public class ExcelDataProvider {

    @DataProvider(name = "excelSheetNameAsMethodName")
    public static Object[][] excelSheetNameAsMethodName() throws Exception {
        File file = new File("src/test/resources/TestData/EnterCredentialsTest.xlsx" );
        System.out.println("Opening Excel File:" + file.getAbsolutePath());
        return ExcelUtils.getTableArray(file.getAbsolutePath());
    }
}
