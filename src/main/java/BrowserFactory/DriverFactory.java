package BrowserFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    Logger log = Logger.getLogger(DriverFactory.class);

    /* for browser launching */
    public WebDriver init_driver(String browser) {


        if (browser.equals("chrome")) {
            /*ChromeOptions co = new ChromeOptions();
            co.setHeadless(false);*/
            tlDriver.set(new ChromeDriver(BrowserCapabilities.getLocalChromeCapabilities()));
        } else if (browser.equals("edge")) {
            EdgeOptions eo = new EdgeOptions();
            eo.setHeadless(false);
            tlDriver.set(new EdgeDriver(eo));
        } else if (browser.equals("firefox")) {
            FirefoxOptions fo = new FirefoxOptions();
            fo.setHeadless(false);
            tlDriver.set(new FirefoxDriver(fo));
        } else {
            System.out.println("Please pass the correct browser value: " + browser);
        }
        log.info("browser name is: " + browser);
        log.info("launching " + browser + " browser");

        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
       /*DriverFactory.getDriver().get("https://eformsnew.zensar.com/eformsDev/Login");*/
        return getDriver();
    }

    /*public WebDriver remoteDriver(String browserName, String browser_version, String os, String os_version, Method name) {
        System.out.println("browser name is : " + browserName);
        String methodName = name.getName();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", browserName);
        caps.setCapability("os", os);
        caps.setCapability("os_version", os_version);
        caps.setCapability("browser_version", browser_version);
        caps.setCapability("name", methodName);

        if (browserName.equals("Chrome")) {
            tlDriver.set(new ChromeDriver());

        } else if (browserName.equals("Firefox")) {
            tlDriver.set(new FirefoxDriver());
            caps.setCapability("browser", "Firefox");
        }
        String URL= "http://localhost:4444/";
        try {
            tlDriver.set(new RemoteWebDriver(new URL(URL), caps));
            DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return getDriver();
    }*/

  /*  public WebDriver RemoteDriver() {
        String URL= "http://localhost:4444/";
        try {
            tlDriver.set(new RemoteWebDriver(new URL(URL), BrowserCapabilities.remoteDriverCap()));
            DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return getDriver();
    }*/

    /*driver initialized */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

}
