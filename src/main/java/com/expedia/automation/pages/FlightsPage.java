package com.expedia.automation.pages;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Flights search opened from the homepage Flights tab. */
public final class FlightsPage extends BasePage {

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    public FlightsPage open() {
        openHome();
        select(Product.FLIGHTS);
        return this;
    }

    public boolean roundTripSelected() {
        return "true".equals(driver.findElement(By.cssSelector("a[href*='flightType=ROUND_TRIP']"))
                .getAttribute("aria-selected"));
    }

    public boolean locationsVisible() {
        WebElement form = form(Product.FLIGHTS);
        return form.findElement(By.id("origin_select-input")).isDisplayed()
                && form.findElement(By.id("destination_select-input")).isDisplayed();
    }

    public boolean searchReady() {
        return searchReady(Product.FLIGHTS);
    }

    /** Switches off the default round trip and returns the single departure date. */
    public LocalDate chooseOneWay() {
        By oneWay = By.cssSelector("a[href*='flightType=ONE_WAY']");
        wait.until(ExpectedConditions.elementToBeClickable(oneWay)).click();
        wait.until(ExpectedConditions.attributeToBe(oneWay, "aria-selected", "true"));
        wait.until(driver -> {
            String label = form(Product.FLIGHTS)
                    .findElement(By.cssSelector("button[data-stid='uitk-date-selector-input1-default']"))
                    .getAttribute("aria-label");
            return label != null && label.startsWith("Date,");
        });
        return LocalDate.parse(hiddenValue(
                Product.FLIGHTS, By.cssSelector("input[name^='EGDSSingleDatePicker-Date']")));
    }
}
