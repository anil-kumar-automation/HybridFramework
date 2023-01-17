package Listnerslenin;

import BrowserFactory.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ExtentReporter implements ITestListener {
    private static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @SuppressWarnings("deprecation")
    private void createExtentTest(ITestResult result){
        String owner ;
        String testCategory;
        try{
            owner = /*result.getMethod().getMethodName()*/ "Anil Kumar";
        }catch (NullPointerException e){
            owner = "NO_AUTHOR";
        }
        try{
            testCategory =/* result.getMethod().getMethodName()*/ "Regression";
        }catch (NullPointerException e){
            testCategory= "NO_CATEGORY";
        }
        ExtentTest test = extent.createTest(result.getTestClass().getRealClass().getSimpleName()+"::"+result.getMethod().getMethodName()).assignAuthor(owner).assignCategory(testCategory);
        extentTest.set(test);
    }

    @Override
    public void onTestStart(ITestResult result) {
        createExtentTest(result);

    }
    @Override
    public void onTestSuccess(ITestResult result) {

        if(result.getStatus()==2){
            onTestFailure(result);
            return;
        }
        String logText = "<b>Test Method"+ result.getMethod().getMethodName() +"Successful</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS,m);
        extentTest.get().info("TestCase execution finished.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionName = result.getThrowable().getClass().getName();
        String message = "";
        try{
            message = result.getThrowable().getMessage().replaceAll("<","< ").replaceAll(">"," >")+"\n";
        }catch (NullPointerException e){
            message="";
        }
        String exceptionMessage = exceptionName+ ": "+message
                + Arrays.toString(result.getThrowable().getStackTrace()).replaceAll(", ","\n");
        extentTest.get().fail("<details><sumary><b><font color=red>" +"Exception Occurred,click to see details: <br>"
                +"</font></b></summary>" + exceptionMessage.replaceAll("\n","<br>")+"</details>");
        String path = takeScreenshot(methodName);
        try{
            if (new File(path).exists())
                extentTest.get().fail("Test failed, cannot attach screenshot");
            else
                extentTest.get().fail("<b><font color=red>" +"Screenshot of failure" + "</font></b>",
                        MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }catch (Exception e){
            extentTest.get().fail("Test failed, cannot attach screenshot");
        }
        String logText = "<b>Test Method " + methodName + " Failed</>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL,m);
        extentTest.get().info("TestCase execution finished.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREY);
        extentTest.get().log(Status.SKIP,m);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null)
            extent.flush();
    }

    private String takeScreenshot(String methodName){
        String fileName = methodName +".png";
        String directory = "./Reports/Screenshots/";
        new File(directory).mkdirs();
        String path = directory+fileName;
        try {
            {
                File screnshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screnshot,new File(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "./"+fileName;
    }

    public static void info(String message){
        ExtentReporter.extentTest.get().log(Status.PASS,message);
    }

    public static void warn(String message){
        ExtentReporter.extentTest.get().log(Status.WARNING,message);
    }

    public static void error(String message){
        ExtentReporter.extentTest.get().log(Status.FAIL,"<details><summary><b><font color=red>" +"Exception Occurred,click to see details: <br>"
                +"</font></b></summary>" + message.replaceAll("\n","<br>")+"</details>");
    }

    public static void startTestCase(String sTestCaseName){
        ExtentReporter.extentTest.get().info("Starting execution of test <b>"+sTestCaseName+"</b>");
    }

    public static void endTestCase(String message){
        ExtentReporter.extentTest.get().info("Testcase execution finished");
    }

}
