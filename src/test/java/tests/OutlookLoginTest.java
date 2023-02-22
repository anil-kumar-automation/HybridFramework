package tests;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Properties;

import BrowserFactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.selenium.CommonActions;

public class OutlookLoginTest {

    public CommonActions ca;
    public DriverFactory df;
    private Properties props;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        ca = new CommonActions();
        df = new DriverFactory();
        df.init_driver("chrome");
    }

    @Test
    public void loginTest() throws Exception {

        // Go to Outlook login page
        df.getDriver().get("https://zenfederation.zensar.com/adfs/ls/?client-request-id=4d220ea1-bb54-1797-4a5f-1797dd63cb81&username=&wa=wsignin1.0&wtrealm=urn%3afederation%3aMicrosoftOnline&wctx=estsredirect%3d2%26estsrequest%3drQQIARAAjZE9iNNwAMXzv_Ri29OzuJybcjgJafNvemlSFKz9_k4_ILRL-DdN2jRN_zFJPzc3x9sOThDUQalODiLn4iAcnEtnQVAQOQThcJAbbXFx0zc8Hrw3vd9NEgZh7AbzR2F67TSjaZBW1HX6S_YVf-Dx9m6p8fZgJy_vHHxSPtw_BP65OnSQHVSwuQDXeq5rObFQCI_cAcZGEGuarqjrMoQnKPQGgCUApwAsNqIcy8NwhIdChI9AlovCvSBkeYbb0xDNcVyHjiBVoYXVjo6ygsYhGBaEtvBx43IlPnJ74bVhW5-rPzd8GrZN2cKOe0i-BsnmaJrEuUk6nijK2G1biHdtlkkqnXjz7mBerZVEqWoY3VR3Uu1nhXEvOraKZqYUrVei9URBN1PpQVXCRavAZHpVNMvNunap4ig1vi6hqYIadrZfG2SySb1es2Z1esA2uNG4ztiZSLlSm5uM2Jqjcjs7KI70lAGnEzmfleTRvbQuWEVDQZycEqDYcfh03pGHQ4EuikJ8siD_C8IrklqdaeLhCUlhSx3qnaUHfPaA756LDBnzev0B4ipxnTj3gCebK15fnm29yKmFO89vPfz17VgkTjZDbtywBYzYvinmMomZC7lME9q5Zt6QzF4rX-Vb5R6KSK46d25zMbhPgX2KOqJ8XjJA7JIJEZ5S4IwCDy4QR75_8V5ugXeXiPPtl0_PHh1_ff8j-xs1#");

        //Authentications
        DriverFactory.getDriver().findElement(By.id("userNameInput")).sendKeys("s.kode@zensar.com");
        DriverFactory.getDriver().findElement(By.id("passwordInput")).clear();
        Thread.sleep(2000);
        DriverFactory.getDriver().findElement(By.id("passwordInput")).sendKeys("Sriman@6303078199");
        DriverFactory.getDriver().findElement(By.id("submitButton")).click();
        Thread.sleep(5000);
        DriverFactory.getDriver().findElement(By.id("idChkBx_SAOTCAS_TD")).click();
        Thread.sleep(1000);
        DriverFactory.getDriver().findElement(By.id("KmsiCheckboxField")).click();
        Thread.sleep(5000);
        DriverFactory.getDriver().findElement(By.id("idSIButton9")).click();
        Thread.sleep(5000);

        // Compose new email with first recipient
        DriverFactory.getDriver().findElement(By.xpath("//button[@class='splitPrimaryButton root-185']")).click();
        DriverFactory.getDriver().findElement(By.cssSelector("div[aria-label='To']")).sendKeys("anil.singala@zensar.com");
        Thread.sleep(2000);
        DriverFactory.getDriver().findElement(By.cssSelector("div[aria-label='To']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //Alerts
        int durationInSeconds = 10;
        Duration timeout = Duration.ofSeconds(durationInSeconds);
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.alertIsPresent());

        //Handle alert
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.accept();

        //second recipient
        DriverFactory.getDriver().findElement(By.cssSelector("div[aria-label='To']")).sendKeys("a.chavan@zensar.com");
        Thread.sleep(2000);
        DriverFactory.getDriver().findElement(By.cssSelector("div[aria-label='To']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //Subject and Body message
        DriverFactory.getDriver().findElement(By.xpath("//input[contains(@class, 'ms-TextField-field')]")).sendKeys("XYZ-Report");
        Thread.sleep(1000);
        DriverFactory.getDriver().findElement(By.xpath("//div[contains(@class, 'dFCbN') and contains(@class, 'k1Ttj') and contains(@class, 'dPKNh') and contains(@class, 'DziEn')]")).sendKeys("Hi all, XYZ-Report");

        //Attachment
        DriverFactory.getDriver().findElement(By.xpath("//*[@id='docking_InitVisiblePart_0']/div/div[3]/div[5]/div[2]/div/div/div/div/div[1]/div/div[1]/button/span/div/div/img")).click();

        //Browse-this-computer
        DriverFactory.getDriver().findElement(By.xpath("//span[text()='Browse this computer']")).click();

        //FilePath
        StringSelection stringSelection = new StringSelection("C:\\Users\\SK66921\\Documents\\Git\\HybridFramework\\content.pdf");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        //To stimulate uploading process
        Robot robot = new Robot();
        robot.setAutoDelay(4000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.setAutoDelay(3000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //Send email
        DriverFactory.getDriver().findElement(By.xpath("//button[contains(@class, 'ms-Button') and contains(@class, 'ms-Button--primary') and contains(@class, 'ms-Button--hasMenu')]")).click();
        System.out.println("Email Sent");

    }

    @AfterClass
    public void tearDown() {
        // Close browser
        DriverFactory.getDriver().quit();
    }

}

