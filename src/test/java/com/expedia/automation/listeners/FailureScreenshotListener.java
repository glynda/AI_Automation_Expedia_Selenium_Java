package com.expedia.automation.listeners;

import com.expedia.automation.driver.DriverFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Saves a screenshot for a failed test while the browser is still open. */
public final class FailureScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver;
        try {
            driver = DriverFactory.driver();
        } catch (IllegalStateException exception) {
            return;
        }
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }
        Path target = Path.of(
                "target",
                "screenshots",
                result.getTestClass().getRealClass().getSimpleName() + "-" + result.getName() + ".png");
        try {
            Files.createDirectories(target.getParent());
            Files.copy(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING);
            System.err.println("Saved failure screenshot to " + target.toAbsolutePath());
        } catch (IOException exception) {
            System.err.println("Could not save screenshot to " + target + ": " + exception.getMessage());
        }
    }
}
