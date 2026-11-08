package com.expedia.automation.pages;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Packages search. Stay and flight are selected when the tab opens. */
public final class PackagesPage extends BasePage {

    public PackagesPage(WebDriver driver) {
        super(driver);
    }

    public PackagesPage open() {
        openHome();
        select(Product.PACKAGES);
        return this;
    }

    public boolean staySelected() {
        return pill("package-type-selector-stay");
    }

    public boolean flightSelected() {
        return pill("package-type-selector-flight");
    }

    public boolean carSelected() {
        return pill("package-type-selector-car");
    }

    public String formName() {
        return form(Product.PACKAGES).getAttribute("name");
    }

    public boolean locationsVisible() {
        return form(Product.PACKAGES).findElement(By.id("origin_select-input")).isDisplayed()
                && form(Product.PACKAGES).findElement(By.id("destination_select-input")).isDisplayed();
    }

    public LocalDate startDate() {
        return date(Product.PACKAGES, "EGDSDateRangePicker-StartDate");
    }

    public LocalDate endDate() {
        return date(Product.PACKAGES, "EGDSDateRangePicker-EndDate");
    }

    public boolean searchReady() {
        return searchReady(Product.PACKAGES);
    }

    private boolean pill(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }
}
