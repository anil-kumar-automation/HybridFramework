package BrowserFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    Logger log = Logger.getLogger(DriverFactory.class);

    /* for browser launching */
    public WebDriver init_driver(String browser) {


        if (browser.equals("chrome")) {
            tlDriver.set(new ChromeDriver(BrowserCapabilities.getLocalChromeCapabilities()));
        } else if (browser.equals("edge")) {
            tlDriver.set(new EdgeDriver(BrowserCapabilities.getEdgeCapabilitiesWithHeadless()));
        } else if (browser.equals("firefox")) {
            tlDriver.set(new FirefoxDriver(BrowserCapabilities.getFirefoxCapabilitiesWithHeadless()));
        } else {
            System.out.println("Please pass the correct browser value: " + browser);
        }
        log.info("browser name is: " + browser);
        log.info("launching " + browser + " browser");

        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().deleteAllCookies();
        return getDriver();
    }


    public WebDriver RemoteDriver() {
        try {
            tlDriver.set(new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), BrowserCapabilities.remoteDriverCap()));
            DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return getDriver();
    }



    /*driver initialized */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
