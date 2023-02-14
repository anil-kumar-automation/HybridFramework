package tests;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class ExcelTestRunner {

    public static void main(String[] args) throws ClassNotFoundException {
        String excelPath ="C:\\Users\\SK66921\\Documents\\Git\\HybridFramework\\src\\test\\resources\\TestData\\DynamicMulti.xlsx";
        // Read the class names from the Excel sheet
        List<String> classNames = ExcelUtils.getClassNamesFromExcelSheet(excelPath,"Sheet1");

        // Create TestNG object
        TestNG testng = new TestNG();

        // Create a TestListenerAdapter to listen to test results
        TestListenerAdapter tla = new TestListenerAdapter();

        // Add the TestListenerAdapter to TestNG
        testng.addListener(tla);

        // Create a new test suite and classes running in parallel
        XmlSuite suite = new XmlSuite();
        suite.setName("Dynamic Test Suite");
        suite.setParallel(XmlSuite.ParallelMode.CLASSES);

        // Create a new test with the specified class names
        XmlTest test = new XmlTest(suite);
        test.setName("Dynamic Test");
        test.setXmlClasses(getXmlClasses(classNames));

        // Add the test to the suite
        suite.setTests(new ArrayList<XmlTest>() {{
            add(test);
        }});

        // Create a list of suites to run
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        // Set the suites to run on TestNG
        testng.setXmlSuites(suites);

        // Run the tests
        testng.run();

        // Get the results
        List<ITestResult> passedTests = tla.getPassedTests();
        List<ITestResult> failedTests = tla.getFailedTests();
        List<ITestResult> skippedTests = tla.getSkippedTests();

        // Print the results
        System.out.println("Passed Tests:");
        for (ITestResult result : passedTests) {
            System.out.println(result.getName() + " passed");
        }

        System.out.println("\nFailed Tests:");
        for (ITestResult result : failedTests) {
            System.out.println(result.getName() + " failed");
        }

        System.out.println("\nSkipped Tests:");
        for (ITestResult result : skippedTests) {
            System.out.println(result.getName() + " skipped");
        }
    }

    /**
     * Get a list of XmlClasses for the specified class names.
     */
    private static List<XmlClass> getXmlClasses(List<String> classNames) throws ClassNotFoundException {
        List<XmlClass> xmlClasses = new ArrayList<>();
        for (String className : classNames) {
            XmlClass xmlClass = new XmlClass(className);
            List<XmlInclude> xmlIncludes = new ArrayList<>();
            Method[] methods = Class.forName(className).getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test annotation = method.getAnnotation(Test.class);
                    if (annotation != null) {
                        int priority = annotation.priority();
                        XmlInclude xmlInclude = new XmlInclude(method.getName(), priority);
                        xmlIncludes.add(xmlInclude);
                    }
                }
            }
            xmlClass.setIncludedMethods(xmlIncludes);
            xmlClasses.add(xmlClass);
        }
        return xmlClasses;
    }
}
class ExcelUtils {
    public static List<String> getClassNamesFromExcelSheet(String fileName, String sheetName) {
        List<String> classNames = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String className = cell.getStringCellValue();
                    classNames.add(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classNames;
    }
}





