package BrowserFactory;

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

    /* for browser launching */
    public WebDriver init_driver(String browser) {

        System.out.println("browser name is: " + browser);

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

        DriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /*DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));*/
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().get("https://eformsnew.zensar.com/eformsDev/Login");
        return getDriver();
    }

    /*driver initialized */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
