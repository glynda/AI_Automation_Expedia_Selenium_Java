package com.expedia.automation.tests;

import com.expedia.automation.pages.CruisesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CruisesPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "cruises"})
    public void cruisesSearchFormIsReady() {
        CruisesPage cruises = new CruisesPage(driver()).open();

        Assert.assertTrue(cruises.goingToVisible(), "Going to is not visible");
        Assert.assertTrue(cruises.departureLabel().startsWith("Departing between"), cruises.departureLabel());
        Assert.assertTrue(cruises.durationVisible(), "Duration control is not visible");
        Assert.assertTrue(cruises.searchReady(), "Cruises search is not ready");
    }

    @Test(groups = {"regression", "cruises"})
    public void cruiseDepartureAndDurationAreBounded() {
        CruisesPage cruises = new CruisesPage(driver()).open();

        Assert.assertFalse(cruises.endDate().isBefore(cruises.startDate()));
        Assert.assertTrue(cruises.minNights() >= 1, "min nights was " + cruises.minNights());
        Assert.assertTrue(cruises.maxNights() >= cruises.minNights(),
                cruises.minNights() + " to " + cruises.maxNights());
    }
}
