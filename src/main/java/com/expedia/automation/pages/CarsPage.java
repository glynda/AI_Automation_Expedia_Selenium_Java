package com.expedia.automation.pages;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/** Cars search opened from the homepage Cars tab. */
public final class CarsPage extends BasePage {

    public CarsPage(WebDriver driver) {
        super(driver);
    }

    public CarsPage open() {
        openHome();
        select(Product.CARS);
        return this;
    }

    public boolean locationsVisible() {
        return form(Product.CARS).findElement(By.id("pick_up_location-input")).isDisplayed()
                && form(Product.CARS).findElement(By.id("drop_off_location-input")).isDisplayed();
    }

    public String pickUpTime() {
        return selectedOption("pick_up_time");
    }

    public String dropOffTime() {
        return selectedOption("drop_off_time");
    }

    public LocalDate startDate() {
        return date(Product.CARS, "EGDSDateRangePicker-StartDate");
    }

    public LocalDate endDate() {
        return date(Product.CARS, "EGDSDateRangePicker-EndDate");
    }

    public boolean searchReady() {
        return searchReady(Product.CARS);
    }

    private String selectedOption(String id) {
        return new Select(form(Product.CARS).findElement(By.id(id)))
                .getFirstSelectedOption()
                .getText()
                .trim();
    }
}
