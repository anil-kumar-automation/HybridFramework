package listeners;
import BrowserFactory.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentTestNGIReporterListener {
    static ExtentReports extent;
    public static ExtentReports extentReportGenerator(){
       //String path = System.getProperty("user.dir")+"\\ScreenShotReports\\ExtentReport.html";
        String path=System.getProperty("user.dir")+"/test-output/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Zensar Extent Reports");
        reporter.config().setReportName("ExtentSparkReports - BFSI");
        extent = new ExtentReports();
        extent.setSystemInfo("Application", "Eform And Hrm");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("Execution Mode", "Automated");
        extent.setSystemInfo("Java Version", "1.8.0");
        extent.setSystemInfo("Author", "Zensar Technologies");

        // allow automatic saving of media files relative to the report
        extent.setReportUsesManualConfiguration(true);

        extent.attachReporter(reporter);
        return extent;
    }

    public static String getScreenShotPath(String TestCaseName) throws IOException {
        //Date d = new Date();
        //String screenshotFile=d.toString().replace(":","_").replace(" ", "_")+".png";

        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        //String destpath = System.getProperty("user.dir")+"Screenshot//"+TestCaseName+".png";
        //String destpath = System.getProperty("user.dir")+"Screenshot//"+TestCaseName+screenshotFile;
        //String destpath ="Screenshots//"+TestCaseName+screenshotFile;
        //String destpath ="./Screenshots/"+TestCaseName+".\\"+screenshotFile; //help to save screenshots in folder level

        String destpath =System.getProperty("user.dir")+"./Screenshots/"+TestCaseName+".png";
        File file = new File(destpath);
        FileUtils.copyFile(source,file);
        return destpath;
    }


}
