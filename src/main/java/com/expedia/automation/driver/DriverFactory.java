package com.expedia.automation.driver;

import com.expedia.automation.config.FrameworkConfig;
import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/** One Chrome session per thread. Selenium Manager resolves the driver. */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVERS = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void start() {
        if (DRIVERS.get() != null) {
            return;
        }
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setExperimentalOption("prefs", Map.of("intl.accept_languages", "en-US,en"));
        options.addArguments(
                "--window-size=1440,1200",
                "--lang=en-US",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage");
        if (FrameworkConfig.headless()) {
            options.addArguments("--headless=new");
        }
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        DRIVERS.set(driver);
    }

    public static WebDriver driver() {
        WebDriver driver = DRIVERS.get();
        if (driver == null) {
            throw new IllegalStateException("Call DriverFactory.start() before using the driver");
        }
        return driver;
    }

    public static void stop() {
        WebDriver driver = DRIVERS.get();
        if (driver != null) {
            driver.quit();
            DRIVERS.remove();
        }
    }
}
