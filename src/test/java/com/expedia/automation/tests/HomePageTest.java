package com.expedia.automation.tests;

import com.expedia.automation.pages.Product;
import com.expedia.automation.pages.StaysPage;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "home"})
    public void staysSearchFormIsReady() {
        StaysPage stays = new StaysPage(driver()).open();

        Assert.assertTrue(stays.title().contains("Expedia"), stays.title());
        Assert.assertTrue(stays.logoVisible(), "Header logo is missing");
        Assert.assertTrue(stays.signInVisible(), "Sign in is missing");
        Assert.assertTrue(stays.searchReady(), "Stays search is not ready");
    }

    @Test(groups = {"regression", "home"})
    public void productTabsUseTheirSearchPaths() {
        StaysPage stays = new StaysPage(driver()).open();

        Assert.assertEquals(
                stays.productTabPaths(),
                Arrays.stream(Product.values()).map(Product::path).sorted().toList());
    }

    @Test(groups = {"regression", "home"})
    public void staysKeepsTypedTextUnresolvedUntilASuggestionIsChosen() {
        StaysPage stays = new StaysPage(driver()).open();

        stays.typeDestination("Seattle");

        Assert.assertTrue(
                stays.selectedDestination().isBlank(),
                "Selected location was " + stays.selectedDestination());
        Assert.assertFalse(stays.endDate().isBefore(stays.startDate()));
        Assert.assertEquals(stays.adultCount(), "2");
        Assert.assertFalse(stays.bundleSelected());
        Assert.assertTrue(stays.bundlePackageType().isBlank(), stays.bundlePackageType());
    }
}
