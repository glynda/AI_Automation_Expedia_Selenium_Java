package com.expedia.automation.pages;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Cruises search opened from the homepage Cruises tab. */
public final class CruisesPage extends BasePage {

    public CruisesPage(WebDriver driver) {
        super(driver);
    }

    public CruisesPage open() {
        openHome();
        select(Product.CRUISES);
        return this;
    }

    public boolean goingToVisible() {
        return form(Product.CRUISES)
                .findElement(By.cssSelector("button[data-stid='destination_form_field-menu-trigger']"))
                .isDisplayed();
    }

    public String departureLabel() {
        return form(Product.CRUISES)
                .findElement(By.cssSelector("button[data-stid='uitk-date-selector-input1-default']"))
                .getAttribute("aria-label");
    }

    public boolean durationVisible() {
        return form(Product.CRUISES).findElement(By.name("duration_span_form_field_input")).isDisplayed();
    }

    public int minNights() {
        return Integer.parseInt(hiddenValue(
                Product.CRUISES, By.cssSelector("input[name^='SearchFormDurationSpanField-Min']")));
    }

    public int maxNights() {
        return Integer.parseInt(hiddenValue(
                Product.CRUISES, By.cssSelector("input[name^='SearchFormDurationSpanField-Max']")));
    }

    public LocalDate startDate() {
        return date(Product.CRUISES, "EGDSDateRangePicker-StartDate");
    }

    public LocalDate endDate() {
        return date(Product.CRUISES, "EGDSDateRangePicker-EndDate");
    }

    public boolean searchReady() {
        return searchReady(Product.CRUISES);
    }
}
