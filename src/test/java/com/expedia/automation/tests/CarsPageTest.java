package com.expedia.automation.tests;

import com.expedia.automation.pages.CarsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarsPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "cars"})
    public void carsSearchFormIsReady() {
        CarsPage cars = new CarsPage(driver()).open();

        Assert.assertTrue(cars.locationsVisible(), "Pick-up and drop-off are not visible");
        Assert.assertFalse(cars.pickUpTime().isBlank(), "Pick-up time has no selection");
        Assert.assertFalse(cars.dropOffTime().isBlank(), "Drop-off time has no selection");
        Assert.assertTrue(cars.searchReady(), "Cars search is not ready");
    }

    @Test(groups = {"regression", "cars"})
    public void carsUseARentalDateRange() {
        CarsPage cars = new CarsPage(driver()).open();

        Assert.assertFalse(cars.endDate().isBefore(cars.startDate()));
    }
}
