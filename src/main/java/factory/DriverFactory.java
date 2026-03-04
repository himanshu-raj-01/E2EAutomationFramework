package factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {

        try {

            if (browser.equalsIgnoreCase("chrome")) {

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // DISABLE PASSWORD MANAGER & BREACH DETECTION
                Map<String, Object> prefs = new HashMap<>();

                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);

                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("safebrowsing.enabled", false);
                prefs.put("safebrowsing.disable_download_protection", true);

                options.setExperimentalOption("prefs", prefs);

                // Disable automation flags
                options.setExperimentalOption("excludeSwitches",
                        new String[]{"enable-automation"});

                options.setExperimentalOption("useAutomationExtension", false);

                // Additional hard disable flags
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-features=PasswordLeakDetection");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-infobars");
                options.addArguments("--incognito");

                driver.set(new ChromeDriver(options));
            }

            else if (browser.equalsIgnoreCase("firefox")) {

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();
                driver.set(new FirefoxDriver(options));
            }

            else {
                throw new RuntimeException("Browser not supported: " + browser);
            }

            driver.get().manage().window().maximize();
            return driver.get();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Driver initialization failed for: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}