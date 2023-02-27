package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class ExcelTestRunner {
    public static void main(String[] args) throws ClassNotFoundException {
        // Read the class names, execution flags, and browsers from the Excel sheet
        List<String> classNames = ExcelUtils.getClassNamesFromExcelSheet();
        List<String> executionFlags = ExcelUtils.getExecutionFlagsFromExcelSheet();
        List<String> browsers = ExcelUtils.getBrowserNamesFromExcelSheet();

        // Create TestNG object
        TestNG testng = new TestNG();

        // Create a TestListenerAdapter to listen to test results
        TestListenerAdapter tla = new TestListenerAdapter();

        // Add the TestListenerAdapter to TestNG
        testng.addListener(tla);

        // Create a new test suite
        XmlSuite suite = new XmlSuite();
        suite.setName("Dynamic Test Suite");

        //Adding listeners to listen to the test results
        suite.addListener("listeners.ExtentReporter");

        //Setting parallel execution for the test classes
        suite.setParallel(XmlSuite.ParallelMode.CLASSES);

        // Create a new test with the specified class names
        XmlTest test = new XmlTest(suite);
        test.setName("Dynamic Test");
        test.setXmlClasses(getXmlClasses(classNames, executionFlags, browsers));

        // Add the tests to the suite
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

        // Get the results of the test cases
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
     * Only include classes that have execution flag set to "Yes".
     * Pass the browser value to the @Parameter("browser") annotation.
     */
    private static List<XmlClass> getXmlClasses(List<String> classNames, List<String> executionFlags, List<String> browsers)
            throws ClassNotFoundException {
        List<XmlClass> xmlClasses = new ArrayList<>();
        for (int i = 0; i < classNames.size(); i++) {
            String className = classNames.get(i);
            if (executionFlags.get(i).equalsIgnoreCase("Yes")) {
                XmlClass xmlClass = new XmlClass(Class.forName(classNames.get(i)));
                List<XmlInclude> xmlIncludes = new ArrayList<>();
                Method[] methods = Class.forName(className).getMethods();
                for (Method method : methods) {
                    if (method.getAnnotation(org.testng.annotations.Test.class) != null) {
                        String methodName = method.getName();
                        int priority = method.getAnnotation(org.testng.annotations.Test.class).priority();
                        XmlInclude xmlInclude = new XmlInclude(methodName, priority);
                        xmlIncludes.add(xmlInclude);
                    }
                }
                xmlClass.setIncludedMethods(xmlIncludes);
                if (i < browsers.size()) {
                    xmlClass.getLocalParameters().put("browser", browsers.get(i));
                }
                xmlClasses.add(xmlClass);
            }
        }
        return xmlClasses;
    }


}

class ExcelUtils {
    private static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/TestData/Dynamic_Multi.xlsx";

    public static List<String> getClassNamesFromExcelSheet() {
        List<String> classNames = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row header = sheet.getRow(0);
            int classNameIndex = -1;
            for (int i = 0; i < header.getLastCellNum(); i++) {
                Cell cell = header.getCell(i);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase("TestCaseClasses")) {
                    classNameIndex = i;
                    break;
                }
            }
            if (classNameIndex == -1) {
                throw new RuntimeException("Header \"TestCaseClasses\" not found in the Excel sheet.");
            }
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Cell cell = row.getCell(classNameIndex);
                if (cell != null) {
                    classNames.add(cell.getStringCellValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNames;
    }

    public static List<String> getExecutionFlagsFromExcelSheet() {
        List<String> executionFlags = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row header = sheet.getRow(0);
            int executionFlagIndex = -1;
            for (int i = 0; i < header.getLastCellNum(); i++) {
                Cell cell = header.getCell(i);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase("ExecutionFlags")) {
                    executionFlagIndex = i;
                    break;
                }
            }
            if (executionFlagIndex == -1) {
                throw new RuntimeException("Header \"Execution\" not found in the Excel sheet.");
            }
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Cell cell = row.getCell(executionFlagIndex);
                if (cell != null) {
                    String cellValue = cell.getStringCellValue().toLowerCase();
                    if (cellValue.equals("yes") || cellValue.equals("y")) {
                        executionFlags.add("Yes");
                    } else {
                        executionFlags.add("No");
                    }
                } else {
                    executionFlags.add("No");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return executionFlags;
    }
    public static List<String> getBrowserNamesFromExcelSheet() {
        List<String> browserNames = new ArrayList<>();
        try {
            // Open the Excel sheet
            FileInputStream file = new FileInputStream(EXCEL_FILE_PATH);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Get the browser names from the specified column in the sheet
            int browserColumnIndex = 2; // Change this to the index of the browser name column in your sheet
            for (Row row : sheet) {
                Cell cell = row.getCell(browserColumnIndex);
                if (cell != null ) {
                    String browserName = cell.getStringCellValue().trim();
                    if (!browserName.isEmpty()) {
                        browserNames.add(browserName);
                    }
                }
            }

            // Close the Excel sheet
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return browserNames;
    }

}






