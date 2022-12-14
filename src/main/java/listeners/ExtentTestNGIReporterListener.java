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
        //String path = System.getProperty("user.dir")+"\\Reports\\ExtentReport.html";
        String path = System.getProperty("user.dir")+"./Reports/" + "ExtentReport"+".html"; //relative path it's good view to manage in extent report jenkins as well
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Zensar ExtentSparkReports");
        reporter.config().setReportName("BFSI - ExtentSparkReports");
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

        //String destpath = System.getProperty("user.dir")+"\\Reports\\"+TestCaseName+".png";

        String destpath = "./Reports/Screenshots/"+ TestCaseName+ System.currentTimeMillis()+ ".png";  //relative path it's good view to manage in extent report jenkins as well
        File file = new File(destpath);
        FileUtils.copyFile(source,file);
        return destpath;
    }
}
