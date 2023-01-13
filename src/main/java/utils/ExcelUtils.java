package utils;

import oracle.security.crypto.util.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtils {
    private static XSSFSheet xlsxWorkSheet;
    private static XSSFWorkbook xlsxWorkBook;
    private static XSSFCell xlsxCell;
  /*  @SuppressWarnings("unused")*/
    private static XSSFRow xlsxRow;

    private static HSSFSheet xlsWorkSheet;
    private static HSSFWorkbook xlsWorkBook;
    private static HSSFCell xlsCell;
    /*@SuppressWarnings("unused")*/
    private static HSSFRow xlsRow;

    /**
     * To get the Excel-XLSX File with Path and SheetName
     */
    public static void getExcelFile(String Path, String SheetName) throws Exception {
        try {
            File file = new File(Path);
            if (file.getAbsolutePath().endsWith(".xlsx")) {
                FileInputStream fis = new FileInputStream(file);
                xlsxWorkBook = new XSSFWorkbook(fis);
                xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);
            } else if (file.getAbsolutePath().endsWith(".xls")) {
                FileInputStream fis = new FileInputStream(file);
                xlsWorkBook = new HSSFWorkbook(fis);
                xlsWorkSheet = xlsWorkBook.getSheet(SheetName);
            }
        } catch (Exception e) {
            throw (e);
        }
    }


    /**
     * To Return the Excel-XLSX Values given Path to the File and Sheet Name
     */
    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        Object[][] tabArray = null;
        try {
            File file = new File(FilePath);
            if (file.getAbsolutePath().endsWith(".xlsx")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsxWorkBook = new XSSFWorkbook(ExcelFile);
                xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsxRowCount();
                int totalCols = ExcelUtils.xlsxColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLSX(i, j);
                        cj++;
                    }
                    ci++;
                }
            } else if (file.getAbsolutePath().endsWith(".xls")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsWorkBook = new HSSFWorkbook(ExcelFile);
                xlsWorkSheet = xlsWorkBook.getSheet(SheetName);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsRowCount();
                int totalCols = ExcelUtils.xlsColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLS(i, j);
                        cj++;
                    }
                    ci++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Could not Find the Excel File/Sheet");
        } catch (Exception e) {
            throw new Exception("Could not Open the Excel File");
        }
        return (tabArray);
    }


    /**
     * To Return the Excel-XLSX Values given Path to the File
     */
    public static Object[][] getTableArray(String FilePath) throws Exception {
        Object[][] tabArray = null;
        try {
            File file = new File(FilePath);
            if (file.getAbsolutePath().endsWith(".xlsx")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsxWorkBook = new XSSFWorkbook(ExcelFile);
                xlsxWorkSheet = xlsxWorkBook.getSheetAt(0);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsxRowCount();
                int totalCols = ExcelUtils.xlsxColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLSX(i, j);
                        cj++;
                    }
                    ci++;
                }
            } else if (file.getAbsolutePath().endsWith(".xls")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsWorkBook = new HSSFWorkbook(ExcelFile);
                xlsWorkSheet = xlsWorkBook.getSheetAt(0);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsRowCount();
                int totalCols = ExcelUtils.xlsColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLS(i, j);
                        cj++;
                    }
                    ci++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Could not Find the Excel File/Sheet");
        } catch (Exception e) {
            throw new Exception("Could not Open the Excel File");
        }
        return (tabArray);
    }


    /**
     * To get cell data from Excel-XLSX
     */
    public static Object getCellData_XLSX(int RowNum, int ColNum) throws Exception {
        Object CellData = null;
        try {
            xlsxCell = xlsxWorkSheet.getRow(RowNum).getCell(ColNum);
            if (xlsxCell.getCellType() == CellType.STRING) {
                String stringCellData = xlsxCell.getStringCellValue();
                CellData = stringCellData;
            } else if (xlsxCell.getCellType() == CellType.NUMERIC) {
                double numericCellData = xlsxCell.getNumericCellValue();
                CellData = numericCellData;
            } else if (xlsxCell.getCellType() == CellType.BOOLEAN) {
                boolean booleanCellData = xlsxCell.getBooleanCellValue();
                CellData = booleanCellData;
            } else if (xlsxCell.getCellType() == CellType.FORMULA) {
                String formulaCellData = xlsxCell.getCellFormula();
                CellData = formulaCellData;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * To get cell data from Excel-XLS
     */
    public static Object getCellData_XLS(int RowNum, int ColNum) throws Exception {
        Object CellData = null;
        try {
            xlsCell = xlsWorkSheet.getRow(RowNum).getCell(ColNum);
            if (xlsCell.getCellType() == CellType.STRING) {
                String stringCellData = xlsCell.getStringCellValue();
                CellData = stringCellData;
            } else if (xlsCell.getCellType() == CellType.NUMERIC) {
                double numericCellData = xlsCell.getNumericCellValue();
                CellData = numericCellData;
            } else if (xlsCell.getCellType() == CellType.BOOLEAN) {
                boolean booleanCellData = xlsCell.getBooleanCellValue();
                CellData = booleanCellData;
            } else if (xlsxCell.getCellType() == CellType.FORMULA) {
                String formulaCellData = xlsxCell.getCellFormula();
                CellData = formulaCellData;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * To get Excel-XLSX Row Count
     */
    public static int xlsxRowCount() {
        int rowNum = xlsxWorkSheet.getLastRowNum() + 1;
        return rowNum;
    }

    /**
     * To get Excel-XLS Row Count
     */
    public static int xlsRowCount() {
        int rowNum = xlsWorkSheet.getLastRowNum() + 1;
        return rowNum;
    }

    /**
     * To get Excel-XLSX Column Count
     */
    public static int xlsxColumnCount() {
        int rowNum = xlsxWorkSheet.getRow(0).getLastCellNum();
        return rowNum;
    }

    /**
     * To get Excel-XLS Column Count
     */
    public static int xlsColumnCount() {
        int rowNum = xlsWorkSheet.getRow(0).getLastCellNum();
        return rowNum;
    }

    /**This method is used to read excel file using File path and Sheet name parameter*/
    public List<Map<String, String>> getData(String excelFilePath, String sheetName)
            throws InvalidFormatException, IOException {
        Sheet sheet = getSheetByName(excelFilePath, sheetName);
        return readSheet(sheet);
    }
    /**This method is used to read excel file using File path and Sheet number parameter*/
    public List<Map<String, String>> getData(String excelFilePath, int sheetNumber)
            throws InvalidFormatException, IOException {
        Sheet sheet = getSheetByIndex(excelFilePath, sheetNumber);
        return readSheet(sheet);
    }
    /**This method is used to fetch workbook  using File path and Sheet name parameter*/
    private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {
        Sheet sheet = getWorkBook(excelFilePath).getSheet(sheetName);
        return sheet;
    }
    /**This method is used to fetch workbook  using File path and Sheet number parameter*/
    private Sheet getSheetByIndex(String excelFilePath, int sheetNumber) throws IOException, InvalidFormatException {
        Sheet sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
        return sheet;
    }

    private Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(new File(excelFilePath));
    }
    /** This is reading method to fetch value from sheet*/
    private List<Map<String, String>> readSheet(Sheet sheet) {
        Row row;
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
        int headerRowNumber = getHeaderRowNumber(sheet);
        if (headerRowNumber != -1) {
            int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
            int setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                }
                excelRows.add(columnMapdata);
            }
        }
        return excelRows;
    }
    /**This method is fetch Header row count*/
    private int getHeaderRowNumber(Sheet sheet) {
        Row row;
        int totalRow = sheet.getLastRowNum();
        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
            row = getRow(sheet, currentRow);
            if (row != null) {
                int totalColumn = row.getLastCellNum();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    Cell cell;
                    cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (cell.getCellType() == CellType.STRING) {
                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.BOOLEAN) {
                        return row.getRowNum();
                    } else if (cell.getCellType() == CellType.ERROR) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return (-1);
    }
    /**This method is used to fetch Row count*/
    private Row getRow(Sheet sheet, int rowNumber) {
        return sheet.getRow(rowNumber);
    }
    /*This method is used to fetch cell value from workbook*/
    private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
        Cell cell;
        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                    .getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
                        .getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }
        } else {
            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() == CellType.STRING) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, cell.getStringCellValue());
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
                }
            } else if (cell.getCellType() == CellType.BLANK) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, "");
                }
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
                }
            } else if (cell.getCellType() == CellType.ERROR) {
                if (sheet.getRow(sheet.getFirstRowNum())
                        .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
                            .getStringCellValue();
                    columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
                }
            }
        }
        return columnMapdata;
    }

}