/*
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

// aventstack extent code it's supported with 5.0.8 version dependency ,notePoint if use this code for reports just comment 3.1.5 version in pom.xml

public class ExtentTestNGIReporterListener implements IReporter {
    private static final String OUTPUT_FOLDER = "Reports";
    private static final String FILE_NAME = "Extent.html";
    private ExtentReports extent;



public void generateReport(List xmlSuites, List suites, String outputDirectory) {
        init(OUTPUT_FOLDER);
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


    private void init(String outputDirectory) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(outputDirectory + "/" + FILE_NAME);
        htmlReporter.config().setDocumentTitle("ExtentSparkReports - Created by Automation Script");
        htmlReporter.config().setReportName("ExtentSparkReports - Created by Automation Script");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Application", "Eform And Hrm");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("Execution Mode", "Automated");
        extent.setReportUsesManualConfiguration(true);
    }
    public static void assignCategories(ExtentTest test, ITestResult result) {
        for (String group : result.getMethod().getGroups()) {
            test.assignCategory(group);
        }
    }
    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test = null;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test.getModel().setDescription("Method Name is: " + result.getMethod().getMethodName());
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
                test.getModel().timeTaken();
                assignCategories(test, result);

                if (result.getStatus() == ITestResult.FAILURE) {
                    test.log(status.FAIL, "TEST CASE IS FAILED on " + result.getName() + " Method"); //to add name in extent report
                    test.log(status, result.getThrowable());
                } else if (result.getStatus() == ITestResult.SKIP) {
                    test.log(status.SKIP, "Test Case IS SKIPPED on " + result.getName() + "Method");
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.log(status.PASS, "Test Case IS PASSED on " + result.getName() + " Method");
                }
            }
        }

    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();

    }
}
*/
