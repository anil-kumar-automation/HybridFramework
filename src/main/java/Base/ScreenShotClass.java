package Base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenShotClass {

    public String getTimeStamp() {

        Date date = new Date();

        return date.toString().replace(" ", "_").replace(":", "_");

    }

    public String takeScreenshot(String testName, WebDriver driver) throws IOException {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + testName + ".png";

        FileHandler.copy(srcFile, new File(screenshotPath));

        return screenshotPath;

    }
}
