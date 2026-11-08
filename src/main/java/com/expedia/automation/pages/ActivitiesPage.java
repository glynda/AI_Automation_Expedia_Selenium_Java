package com.expedia.automation.pages;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Things to do search opened from the homepage Activities tab. */
public final class ActivitiesPage extends BasePage {

    public ActivitiesPage(WebDriver driver) {
        super(driver);
    }

    public ActivitiesPage open() {
        openHome();
        select(Product.ACTIVITIES);
        return this;
    }

    public boolean destinationVisible() {
        return form(Product.ACTIVITIES).findElement(By.id("destination_form_field-input")).isDisplayed()
                && "Going to".equals(form(Product.ACTIVITIES)
                        .findElement(By.id("destination_form_field-input"))
                        .getAttribute("placeholder"));
    }

    public String selectedDestination() {
        return hiddenValue(
                Product.ACTIVITIES, By.name("EGDSSearchFormLocationField-Selected-destination_form_field"));
    }

    public LocalDate startDate() {
        return date(Product.ACTIVITIES, "EGDSDateRangePicker-StartDate");
    }

    public LocalDate endDate() {
        return date(Product.ACTIVITIES, "EGDSDateRangePicker-EndDate");
    }

    public boolean searchReady() {
        return searchReady(Product.ACTIVITIES);
    }
}
