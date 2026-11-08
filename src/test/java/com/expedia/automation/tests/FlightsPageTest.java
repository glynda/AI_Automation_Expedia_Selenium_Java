package com.expedia.automation.tests;

import com.expedia.automation.pages.FlightsPage;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightsPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "flights"})
    public void roundTripSearchFormIsReady() {
        FlightsPage flights = new FlightsPage(driver()).open();

        Assert.assertTrue(flights.roundTripSelected(), "Roundtrip is not the selected flight type");
        Assert.assertTrue(flights.locationsVisible(), "Origin and destination are not visible");
        Assert.assertTrue(flights.searchReady(), "Flights search is not ready");
    }

    @Test(groups = {"regression", "flights"})
    public void oneWayUsesASingleDepartureDate() {
        FlightsPage flights = new FlightsPage(driver()).open();

        LocalDate departure = flights.chooseOneWay();

        Assert.assertNotNull(departure);
    }
}
