package listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;



// pankaj code:- aventstack extent code it's supported with 5.0.8 version dependency ,notePoint if use this code for reports just comment 3.1.5 version in pom.xml
public class ExtentTestNGIReporterListenerVersion5 implements IReporter {
    private static final String OUTPUT_FOLDER = "Reports";
    private static final String FILE_NAME = "VersionFiveExtentReport.html";
    public static ExtentReports extent;

public void generateReport(List xmlSuites, List suites,String outputDirectory) {
    //ExtentSparkReporter spark  = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/Extent.html");
    ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + "/" + FILE_NAME);
    spark .config().setDocumentTitle("ExtentSparkReports - Zensar Framework");
    spark .config().setReportName("ExtentSparkReports - Efrom Anf Hrm");
    spark .config().setTheme(Theme.STANDARD);
    extent = new ExtentReports();
    extent.attachReporter(spark);
    extent.setSystemInfo("Application", "Eform And Hrm");
    extent.setSystemInfo("Environment", "Dev");
    extent.setSystemInfo("Execution Mode", "Automated");
    extent.setReportUsesManualConfiguration(true);
        for (Object suite : suites) {
            Map<String, ISuiteResult> result = ((ISuite) suite).getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                if (context != null) {
                    buildTestNodes(context.getPassedTests(), Status.PASS);
                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                }
            }
        }
        for (String s : Reporter.getOutput()) {
            extent.addTestRunnerOutput(s);
        }
        extent.flush();
    }


    /*private void init(String outputDirectory) {
        ExtentSparkReporter spark  = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport.html");
        spark .config().setDocumentTitle("ExtentSparkReports - Created by Automation Script");
        spark .config().setReportName("ExtentSparkReports - Created by Automation Script");
        spark .config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "Eform And Hrm");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("Execution Mode", "Automated");
        extent.setReportUsesManualConfiguration(true);
    }*/

    public static void assignCategories(ExtentTest test, ITestResult result) {
        for (String group : result.getMethod().getGroups()) {
            test.assignCategory(group);
        }
    }
    private void buildTestNodes(IResultMap tests, Status status) {

        ExtentTest test = null;
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {

                test = extent.createTest(result.getMethod().getMethodName());

                if (result.getStatus() == ITestResult.SUCCESS) {
                    test.log(status.PASS, "Test Case IS PASSED on " + result.getName() + " Method");
                }
                else if (result.getStatus() == ITestResult.FAILURE) {
                    test.log(status.FAIL, "TEST CASE IS FAILED on " + result.getName() + " Method");
                    test.log(status, result.getThrowable());

                    /*String temp= getScreenshot(DriverFactory.getDriver());
                    test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());*/

                  /* String screenshotPath = ScreenShot.takeScreenshotAtEndOfTest();
                    test.fail("Test Case failed check screenshot below"+test.addScreenCaptureFromPath(screenshotPath));*/
                }
                else if (result.getStatus() == ITestResult.SKIP) {
                    test.log(status.SKIP, "Test Case IS SKIPPED on " + result.getName() + "Method");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
                test.getModel().timeTaken();
                assignCategories(test, result);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();

    }
}
