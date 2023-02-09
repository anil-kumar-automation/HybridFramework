package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtilsDynamicTest {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

/*
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }*/

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int lastRowNum = sheet.getLastRowNum();
        int lastCellNum = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[lastRowNum][lastCellNum];

        for (int i = 0; i < lastRowNum; i++) {
            for (int j = 0; j < lastCellNum; j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

}






