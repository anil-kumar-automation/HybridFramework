package BrowserFactory;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BrowserCapabilities {

    public static DesiredCapabilities remoteDriverCap() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "");
        caps.setCapability("os", "os");
        caps.setCapability("os_version", "os_version");
        caps.setCapability("browser_version", "browser_version");
        caps.setAcceptInsecureCerts(true);
        return caps;
    }

    public static ChromeOptions getLocalChromeCapabilities() {
        ChromeOptions co = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        chromePrefs.put("download.default_directory", "/directory/path");
        co.setExperimentalOption("prefs", chromePrefs);
        co.setAcceptInsecureCerts(true);
        co.setCapability(ChromeOptions.CAPABILITY, co);
        co.addArguments("--start-maximized");
        return co;
    }


    public static ChromeOptions getHeadlessChromeCapability() {

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");

        // configure Chrome to download files to a specific directory
        chromePrefs.put("download.default_directory", "/directory/path");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setHeadless(true);
        // to manage the  connection safe for secure reasons
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        return options;

    }

    public static ChromeOptions incognitoChromeLaunch() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setAcceptInsecureCerts(true);
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--start-maximized");
        co.addArguments("--incognito");
        co = co.merge(caps);
        return co;
    }

    public static FirefoxOptions getFirefoxCapabilitiesWithHeadless() {
        FirefoxOptions fo = new FirefoxOptions();
        fo.setHeadless(true);
        fo.setAcceptInsecureCerts(true);
        fo.addArguments("--start-maximized");
        return fo;
    }


    public static EdgeOptions getEdgeCapabilitiesWithHeadless() {
        EdgeOptions eo = new EdgeOptions();
        eo.setHeadless(false);
        eo.setAcceptInsecureCerts(true);
        eo.addArguments("--start-maximized");
        return eo;
    }

    public static ChromeOptions NewChromeOption() {
        ChromeOptions options = new ChromeOptions();
        //add particular extension
        options.addExtensions(new File("/path/to/extension.crx"));

        //Block pop-up windows
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

        //help to launch Incognito window
        options.addArguments("--incognito");

        //manage headless mode
        options.setHeadless(true);

        //maximized window
        options.addArguments("--start-maximized");

        /*you can use the user-data-dir Chrome command-line switch to tell Chrome which profile to use:*/
        options.addArguments("user-data-dir=/path/to/your/custom/profile");

        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");

        // configure Chrome to download files to a specific directory
        chromePrefs.put("download.default_directory", "/directory/path");
        options.setExperimentalOption("prefs", chromePrefs);

        // to manage the  connection safe for secure reasons
        options.setAcceptInsecureCerts(true);
        options.setCapability(ChromeOptions.CAPABILITY, options);

        // Add the WebDriver proxy capability.
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("myhttpproxy:3337");
        options.setCapability("proxy", proxy);
        return options;
    }
}
