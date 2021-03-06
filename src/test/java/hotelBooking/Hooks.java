package hotelBooking;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Hooks extends BaseUtilities {

    @Before
    public void setUp() {
        String macDriverLocation = getConfigPropertyValue(propertyFile, "mac_driver_location");
        String linuxDriverLocation = getConfigPropertyValue(propertyFile, "linux_driver_location");
        String windowsDriverLocation = getConfigPropertyValue(propertyFile, "windows_driver_location");
        String browserName = getConfigPropertyValue(propertyFile, "browser");
        String runInHeadlessMode = getConfigPropertyValue(propertyFile, "headless");

        switch (browserName) {
            case "chrome":
                try {
                    /**
                     * Driver setup for google chrome web browser
                     */
                    String chromeDriverPath = null;

                    if (this.getOsName().equalsIgnoreCase("Windows")) {
                        chromeDriverPath = macDriverLocation + getConfigPropertyValue(propertyFile, "windows_driver_chrome");
                    } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                        chromeDriverPath = macDriverLocation + getConfigPropertyValue(propertyFile, "mac_driver_chrome");
                    } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                        chromeDriverPath = macDriverLocation + getConfigPropertyValue(propertyFile, "linux_driver_chrome");
                    }
                    logger.info("This is the chrome driver path is :::: " + chromeDriverPath);

                    String absoluteChromeDriverPath = toAbsolutePath(chromeDriverPath);
                    logger.info("This is the chrome driver real path is :::: " + absoluteChromeDriverPath);

                    System.setProperty("webdriver.chrome.driver", absoluteChromeDriverPath);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("test-type");
                    options.addArguments("window-size=1980,1080");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");

                    if (runInHeadlessMode.equalsIgnoreCase("yes")) {
                        options.addArguments("--headless");
                    }
                    driver = new ChromeDriver(options);

                    try {
                        jExecutor = (JavascriptExecutor) driver;
                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
                        driverWait = new WebDriverWait(driver, 10);
                    } catch (Exception ex) {
                        logger.info("The stack trace here happens when I try to maximize the screen");
                        logger.info(ex.getMessage());
                    }
                } catch (WebDriverException ex) {
                    logger.info("Unable to instantiate the chrome driver", ex);
                }
                break;

            case "firefox":
                try {
                    /**
                     * Driver setup for firefox web browser
                     */
                    String firefoxDriverPath = null;
                    logger.info("Firefox ?: " + browserName);
                    if (this.getOsName().equalsIgnoreCase("Windows")) {
                        firefoxDriverPath = windowsDriverLocation + getConfigPropertyValue(propertyFile, "windows_driver_firefox");
                        ;
                    } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                        firefoxDriverPath = macDriverLocation + getConfigPropertyValue(propertyFile, "mac_driver_firefox");
                    } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                        firefoxDriverPath = linuxDriverLocation + getConfigPropertyValue(propertyFile, "linux_driver_firefox");
                    }
                    logger.info("This is the firefox driver path is :::: " + firefoxDriverPath);

                    String absoluteFirefoxDriverPath = toAbsolutePath(firefoxDriverPath);
                    logger.info("This is the chrome driver real path is :::: " + absoluteFirefoxDriverPath);

                    System.setProperty("webdriver.gecko.driver", absoluteFirefoxDriverPath);
                    driver = new FirefoxDriver();

                    try {

                        jExecutor = (JavascriptExecutor) driver;
                        driver.manage().window().maximize();
                        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        driverWait = new WebDriverWait(driver, 10);
                    } catch (Exception ex) {
                        logger.info("The stack trace here happens when I try to maximize the screen");
                        logger.info(ex.getMessage());
                    }
                } catch (WebDriverException ex) {
                    logger.info("Unable to instantiate firefox driver", ex);
                }
                break;

            default:
                    logger.info("Invalid driver selected");

        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (driver != null) {
                logger.info("After features run " + driver);
                if (scenario.isFailed()) {
                    try {
                        String screenshotName = scenario.getName().replaceAll(" ", "_");
                        String timestamp = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime()).replaceAll(":", "-");
                        String encodedBase64 = null;
                        FileInputStream fileInputStream = null;
                        TakesScreenshot screenshot = (TakesScreenshot) driver;
                        File source = screenshot.getScreenshotAs(OutputType.FILE);
                        String destination =System.getProperty("user.dir") +"/target/cucumber-reports/screenshots/" + screenshotName + timestamp + ".png";
                        File finalDestination = new File(destination);
                        FileUtils.copyFile(source, finalDestination);

                        fileInputStream =new FileInputStream(finalDestination);
                        byte[] bytes =new byte[(int)finalDestination.length()];
                        fileInputStream.read(bytes);
                        encodedBase64 = new String(Base64.encodeBase64(bytes));
                        String img = "data:image/png;base64,"+encodedBase64;


                        Reporter.addScreenCaptureFromPath(img.toString());                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
                driver.quit();
                driver = null;
            }
        } catch (WebDriverException ex) {
            logger.info(ex.getMessage());
        }
    }

}
