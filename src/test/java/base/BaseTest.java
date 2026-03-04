package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import factory.DriverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {

        logger.info("Initializing browser: " + browser);

        // Initialize driver
        driver = DriverFactory.initDriver(browser);

        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to application
        driver.get("https://www.saucedemo.com/");
        logger.info("Navigated to application URL");
    }

    @AfterMethod
    public void tearDown() {

        logger.info("Closing browser");

        if (driver != null) {
            DriverFactory.quitDriver();
        }
    }
}