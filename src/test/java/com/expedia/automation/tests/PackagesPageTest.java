package com.expedia.automation.tests;

import com.expedia.automation.pages.PackagesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PackagesPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "packages"})
    public void flightAndHotelPackageIsTheDefault() {
        PackagesPage packages = new PackagesPage(driver()).open();

        Assert.assertTrue(packages.staySelected(), "Stay is not selected");
        Assert.assertTrue(packages.flightSelected(), "Flight is not selected");
        Assert.assertFalse(packages.carSelected(), "Car is selected on the default package");
        Assert.assertEquals(packages.formName(), "packages-search-form-FLIGHT_HOTEL");
        Assert.assertTrue(packages.locationsVisible(), "Origin and destination are not visible");
        Assert.assertTrue(packages.searchReady(), "Packages search is not ready");
    }

    @Test(groups = {"regression", "packages"})
    public void packageDatesAreARange() {
        PackagesPage packages = new PackagesPage(driver()).open();

        Assert.assertFalse(packages.endDate().isBefore(packages.startDate()));
    }
}
