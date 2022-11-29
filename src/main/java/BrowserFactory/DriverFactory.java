package BrowserFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    Logger log = Logger.getLogger(DriverFactory.class);

    /* for browser launching */
    public WebDriver init_driver(String browser) {


        if (browser.equals("chrome")) {
            ChromeOptions co = new ChromeOptions();
            co.setHeadless(false);
            tlDriver.set(new ChromeDriver(co));
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

        /*DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));*/
        getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().deleteAllCookies();
       /*DriverFactory.getDriver().get("https://eformsnew.zensar.com/eformsDev/Login");*/
        return getDriver();
    }

    /*driver initialized */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
