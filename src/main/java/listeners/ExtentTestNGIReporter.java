package listeners;

import BrowserFactory.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;
import utils.ScreenShot;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* aventstack extent code it's supported with 3.1.5 version dependency,,notePoint if use this code for reports just comment 5.1.0 version in pom.xml */
public class ExtentTestNGIReporter  implements IReporter {

   /* private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "Extent.html";*/

        private ExtentReports extent;
        private ExtentTest test;

        public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
            init();

            for (ISuite suite : suites) {
                Map<String, ISuiteResult> result = suite.getResults();

                for (ISuiteResult r : result.values()) {
                    ITestContext context = r.getTestContext();

                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                    buildTestNodes(context.getPassedTests(), Status.PASS);

                }
            }

            for (String s : Reporter.getOutput()) {
                extent.setTestRunnerOutput(s);
            }

            extent.flush();
        }

        private void init() {
            ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport.html");
            htmlReporter.config().setDocumentTitle("ExtentReports - BFSI");
            htmlReporter.config().setReportName("ExtentReports - Efrom And Hrm");
            htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
            htmlReporter.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setReportUsesManualConfiguration(true);

            extent.setSystemInfo("Application", "Eform And Hrm");
            extent.setSystemInfo("Environment", "Regression");
            extent.setSystemInfo("Execution Mode", "Automated");

        }

        private void buildTestNodes(IResultMap tests, Status status) {


            if (tests.size() > 0) {
                for (ITestResult result : tests.getAllResults()) {
                    test = extent.createTest(result.getMethod().getMethodName());

                    for (String group : result.getMethod().getGroups())
                        test.assignCategory(group);

                    if (result.getThrowable() != null) {
                        test.log(status, result.getThrowable());
                    }
                    if(result.getStatus()==ITestResult.FAILURE){
                        test.log(Status.FAIL, "TEST CASE FAILED On "+result.getName()); //to add name in extent report
                        //test.log(Status.FAIL, "TEST CASE FAILED On "+result.getThrowable()); //to add error/exception in extent report


                        /*String screenshotPath = ScreenShot.getScreenshot(DriverFactory.getDriver());
                        test.fail("Test Case failed check screenshot below"+test.addScreenCaptureFromPath(screenshotPath));*/

                        //extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()); //to add screenshot in extent report
                        //extentTest.fail("details").addScreenCaptureFromPath(screenshotPath);
                    }
                    else if(result.getStatus()==ITestResult.SKIP){
                        test.log(Status.SKIP, "Test Case SKIPPED On " + result.getName());
                    }
                    else if(result.getStatus()==ITestResult.SUCCESS){
                        test.log(Status.PASS, "Test Case PASSED On " + result.getName());

                    }
                    /*else {
                        test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                    }*/

                    test.getModel().setStartTime(getTime(result.getStartMillis()));
                    test.getModel().setEndTime(getTime(result.getEndMillis()));
                }
            }
        }

     /*   public void down(ITestResult result) throws IOException {


            if(result.getStatus()==ITestResult.FAILURE){
                test.log(Status.FAIL, "TEST CASE FAILED On "+result.getName()); //to add name in extent report
                test.log(Status.FAIL, "TEST CASE FAILED On "+result.getThrowable()); //to add error/exception in extent report

                String screenshotPath = ScreenShot.getScreenshot(DriverFactory.getDriver());
                test.fail("Test Case failed check screenshot below"+test.addScreenCaptureFromPath(screenshotPath));
                //extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()); //to add screenshot in extent report
                //extentTest.fail("details").addScreenCaptureFromPath(screenshotPath);
            }
            else if(result.getStatus()==ITestResult.SKIP){
                test.log(Status.SKIP, "Test Case SKIPPED On " + result.getName());
            }
            else if(result.getStatus()==ITestResult.SUCCESS){
                test.log(Status.PASS, "Test Case PASSED On " + result.getName());

            }
            extent.flush();
        }
*/
        private Date getTime(long millis) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            return calendar.getTime();
        }
    }
