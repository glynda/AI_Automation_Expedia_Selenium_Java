package com.expedia.automation.pages;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Stays search, which is the form shown when the homepage opens. */
public final class StaysPage extends BasePage {

    public StaysPage(WebDriver driver) {
        super(driver);
    }

    public StaysPage open() {
        openHome();
        select(Product.STAYS);
        return this;
    }

    public String title() {
        return driver.getTitle();
    }

    public boolean logoVisible() {
        return driver.findElement(By.cssSelector("a[data-testid='header-brand-logo-anchor']")).isDisplayed();
    }

    public boolean signInVisible() {
        By signIn = By.cssSelector(
                "header [data-testid='header-menu-button'], header a[href*='/login'], header a[href*='/user/signin']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signIn)).isDisplayed();
    }

    public List<String> productTabPaths() {
        return driver.findElements(By.cssSelector("a[role='tab'][href^='/']")).stream()
                .map(tab -> pathOf(tab.getDomAttribute("href")))
                .sorted()
                .toList();
    }

    public void typeDestination(String city) {
        WebElement input = form(Product.STAYS).findElement(By.id("destination_form_field-input"));
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        input.sendKeys(city);
        wait.until(driver -> city.equals(form(Product.STAYS)
                .findElement(By.id("destination_form_field-input"))
                .getAttribute("value")));
    }

    public String selectedDestination() {
        return hiddenValue(Product.STAYS, By.name("EGDSSearchFormLocationField-Selected-destination_form_field"));
    }

    public LocalDate startDate() {
        return date(Product.STAYS, "EGDSDateRangePicker-StartDate");
    }

    public LocalDate endDate() {
        return date(Product.STAYS, "EGDSDateRangePicker-EndDate");
    }

    public String adultCount() {
        return hiddenValue(Product.STAYS, By.cssSelector("input[name^='EGDSSearchFormTravelersField-Adult']"));
    }

    public boolean bundleSelected() {
        return form(Product.STAYS).findElement(By.id("soft_packages_flight_pill")).isSelected();
    }

    public String bundlePackageType() {
        return hiddenValue(Product.STAYS, By.name("EGDSSoftPackagesPackageType"));
    }

    public boolean searchReady() {
        return searchReady(Product.STAYS);
    }

    private static String pathOf(String href) {
        if (href != null && href.startsWith("http")) {
            return URI.create(href).getPath();
        }
        return href == null ? "" : href;
    }
}
