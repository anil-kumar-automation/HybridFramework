
package listeners;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* relevantcodes extentreports code it's supported with 2.41.2 version dependency */
public class ExtentReportNg implements IReporter {

    public WebDriver driver;
    private ExtentReports extent;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator
                + "FrameworkReports.html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);

            }
        }
        extent.flush();//it notify the test done
        extent.close();
    }



    private void buildTestNodes(IResultMap tests, LogStatus status)  {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {

                test = extent.startTest(result.getTestClass().getName());
                test.setDescription("Method Name is: " + result.getMethod().getMethodName());

                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if(result.getStatus()==ITestResult.FAILURE){
                    test.log(status.FAIL, "TEST CASE IS FAILED on "+result.getName() + " Method"); //to add name in extent report
                    test.log(status, result.getThrowable());
                }

                else if(result.getStatus()==ITestResult.SKIP){
                    test.log(status.SKIP, "Test Case IS SKIPPED on " + result.getName() + "Method");
                }
                else if(result.getStatus()==ITestResult.SUCCESS){
                    test.log(status.PASS, "Test Case IS PASSED on " + result.getName() + " Method");
                }
                extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}

