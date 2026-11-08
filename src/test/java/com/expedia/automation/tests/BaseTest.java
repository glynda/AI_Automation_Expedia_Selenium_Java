package com.expedia.automation.tests;

import com.expedia.automation.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/** A new browser for each test, so a challenged session is not reused. */
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void startBrowser() {
        DriverFactory.start();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverFactory.stop();
    }

    protected WebDriver driver() {
        return DriverFactory.driver();
    }
}
