package com.expedia.automation.pages;

import com.expedia.automation.config.FrameworkConfig;
import com.expedia.automation.support.BotCheckException;
import java.time.Duration;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Shared navigation for the homepage search launcher. */
public abstract class BasePage {

    private static final By COOKIE_CLOSE = By.cssSelector("button.banner-close-button[aria-label='Close']");

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConfig.timeoutSeconds()));
    }

    protected void openHome() {
        driver.get(FrameworkConfig.baseUrl());
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Product.STAYS.formId())));
        } catch (TimeoutException exception) {
            if (isBotCheck()) {
                throw new BotCheckException();
            }
            throw exception;
        }
        dismissCookieBanner();
    }

    protected void select(Product product) {
        By tab = By.cssSelector("a[role='tab'][href='" + product.path() + "']");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(tab));
        if (!"true".equals(link.getAttribute("aria-selected"))) {
            link.click();
        }
        wait.until(ExpectedConditions.attributeToBe(tab, "aria-selected", "true"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(product.formId())));
        if (isBotCheck()) {
            throw new BotCheckException();
        }
    }

    protected WebElement form(Product product) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(product.formId())));
    }

    protected String hiddenValue(Product product, By locator) {
        String value = form(product).findElement(locator).getAttribute("value");
        return value == null ? "" : value.trim();
    }

    protected LocalDate date(Product product, String namePrefix) {
        return LocalDate.parse(hiddenValue(product, By.cssSelector("input[name^='" + namePrefix + "']")));
    }

    protected boolean searchReady(Product product) {
        WebElement button = form(product).findElement(By.id("search_button"));
        return button.isDisplayed() && button.isEnabled() && "Search".equals(button.getText().trim());
    }

    private void dismissCookieBanner() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .pollingEvery(Duration.ofMillis(250))
                    .until(ExpectedConditions.elementToBeClickable(COOKIE_CLOSE))
                    .click();
        } catch (TimeoutException ignored) {
            // The banner is not shown on every visit.
        }
    }

    private boolean isBotCheck() {
        String title = driver.getTitle() == null ? "" : driver.getTitle();
        return title.toLowerCase().contains("bot or not");
    }
}
