package listeners;

import Base.ScreenShotClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;



public class MyListeners extends ScreenShotClass implements ITestListener {

	ExtentTest extentTest;
	ExtentReports extentReports = getExtentReport();

	public ExtentReports getExtentReport(){
		String extentReportPath = "./Reports/ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
		reporter.config().setReportName("TutorialsNinja Automation Results");
		reporter.config().setDocumentTitle("TN Automation Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Operating System","Windows 11");
		extentReports.setSystemInfo("Executed By","Arun Motoori");

		return null;
	}



	@Override
	public void onTestStart(ITestResult result) {

		extentTest = extentReports.createTest(result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS,"Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.fail(result.getThrowable());

		WebDriver driver = null;
		String testName = result.getName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String screenshotPath = takeScreenshot(testName,driver);
			extentTest.addScreenCaptureFromPath(screenshotPath,testName);
		} catch (IOException e) {
			e.printStackTrace();
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

		extentReports.flush();
	}

}
