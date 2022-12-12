package listeners;
import BrowserFactory.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class ExtentTestNGIReporterListener {
    static ExtentReports extent;
    public static ExtentReports extentReportGenerator(){
        String path = System.getProperty("user.dir")+"\\ScreenShotReports\\ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("ExtentSparkReports - Created For Zensar");
        reporter.config().setReportName("ExtentSparkReports - BFSI");
        extent = new ExtentReports();
        extent.setSystemInfo("Application", "Eform And Hrm");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("Execution Mode", "Automated");
        extent.attachReporter(reporter);
        return extent;
    }

    public static String getScreenShotPath(String TestCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destpath = System.getProperty("user.dir")+"\\ScreenShotReports\\"+TestCaseName+".png";
        File file = new File(destpath);
        FileUtils.copyFile(source,file);
        return destpath;
    }
}
