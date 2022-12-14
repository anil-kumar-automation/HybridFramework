package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners  extends ExtentTestNGIReporterListener implements ITestListener {

    ExtentReports extent = ExtentTestNGIReporterListener.extentReportGenerator();
    ExtentTest test;

    String concatenate = ".";


    private static ThreadLocal<ExtentTest> extestTest = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extestTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extestTest.get().log(Status.PASS, "Test Case IS PASSED on " + result.getName() + " Method");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extestTest.get().log(Status.FAIL, "Test Case IS PASSED on " + result.getName() + " Method");
        extestTest.get().fail(result.getThrowable());
        try {
            extestTest.get().addScreenCaptureFromPath(concatenate+getScreenShotPath(result.getMethod().getMethodName()), result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}