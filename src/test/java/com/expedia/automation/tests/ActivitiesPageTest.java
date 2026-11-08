package com.expedia.automation.tests;

import com.expedia.automation.pages.ActivitiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivitiesPageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "activities"})
    public void activitiesSearchFormIsReady() {
        ActivitiesPage activities = new ActivitiesPage(driver()).open();

        Assert.assertTrue(activities.destinationVisible(), "Going to is not visible");
        Assert.assertTrue(activities.selectedDestination().isBlank(), activities.selectedDestination());
        Assert.assertTrue(activities.searchReady(), "Activities search is not ready");
    }

    @Test(groups = {"regression", "activities"})
    public void activitiesUseADateRange() {
        ActivitiesPage activities = new ActivitiesPage(driver()).open();

        Assert.assertFalse(activities.endDate().isBefore(activities.startDate()));
    }
}
