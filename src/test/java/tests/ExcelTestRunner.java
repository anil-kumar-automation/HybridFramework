package tests;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTestRunner {

    public static void main(String[] args) throws IOException {
        // Open the Excel file
        FileInputStream fis = new FileInputStream("src/test/resources/TestData/DynamicTestsHrm.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // Create a TestNG suite
        XmlSuite suite = new XmlSuite();
        suite.setName("TestSuite");
        suite.setParallel(XmlSuite.ParallelMode.CLASSES);

        // Create a TestNG test
        XmlTest test = new XmlTest(suite);
        test.setName("Test");

        // Create a list of XmlClass objects
        List<XmlClass> classes = new ArrayList<XmlClass>();

        // Iterate through the rows of the Excel sheet
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            String className = cell.getStringCellValue();
            System.out.println(className);
            classes.add(new XmlClass(className));
        }

        // Set the list of XmlClass objects to the test
        test.setXmlClasses(classes);

        // Create a list of XmlSuite objects
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);

        // Set the list of XmlSuite objects to TestNG
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);

        // Run the tests
        tng.run();
    }
}
