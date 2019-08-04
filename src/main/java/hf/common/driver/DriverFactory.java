package hf.common.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

/**
 * Created by Sumit Choudhary on 6/15/2019
 */
public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static WebDriver driver = null;

    public static WebDriver getWebDriver() {
        if (driver == null) {
            try {
                String browser = System.getenv("BROWSER");
                //String browser = PropertiesHelper.getPropertyValue("app_browser");
                if (browser == null) {
                    browser = "chrome";
                }

                switch (browser.toUpperCase()) {
                    case "IE":
                        //Launch IE browser
                        logger.info("Launching IE browser ");
                        System.setProperty("webdriver.ie.driver", Paths.get(".").toAbsolutePath().normalize().toString() + System.getenv("DRIVER_PATH"));
                        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                        //Disable protection mode
                        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                        capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions(capabilities);
                        driver = new InternetExplorerDriver(internetExplorerOptions);
                        break;
                    case "FIREFOX":
                        //Launch Firefox browser
                        logger.info("Launching FireFox browser ");
                        System.setProperty("webdriver.gecko.driver", Paths.get(".").toAbsolutePath().normalize().toString() + System.getenv("DRIVER_PATH"));
                        driver = new FirefoxDriver();
                        break;
                    default:
                        //Launch Chrome browser
                        logger.info("Launching Chrome browser");
                        System.setProperty("webdriver.chrome.driver", Paths.get(".").toAbsolutePath().normalize().toString() + System.getenv("DRIVER_PATH"));
                        DesiredCapabilities chromeCapblities = DesiredCapabilities.chrome();
                        chromeCapblities.setBrowserName("chrome");
                        chromeCapblities.setPlatform(org.openqa.selenium.Platform.WINDOWS);

                        driver = new ChromeDriver(chromeCapblities);
                }
            } catch (Exception ex) {
                logger.error("Error in launching browser");
                ex.printStackTrace();
            }
        }
        return driver;
    }


}



