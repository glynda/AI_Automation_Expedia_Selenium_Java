package com.expedia.automation.pages;

/** Search products on the Expedia homepage. Each one swaps the form in place. */
public enum Product {
    STAYS("Stays", "/Hotels", "lodging_search_form"),
    FLIGHTS("Flights", "/Flights", "flight-search-form"),
    CARS("Cars", "/Cars", "car_search_form"),
    PACKAGES("Packages", "/Vacation-Packages", "package-search-form"),
    ACTIVITIES("Things to do", "/Activities", "activity-search-form"),
    CRUISES("Cruises", "/Cruises", "cruise_search_form");

    private final String label;
    private final String path;
    private final String formId;

    Product(String label, String path, String formId) {
        this.label = label;
        this.path = path;
        this.formId = formId;
    }

    public String label() {
        return label;
    }

    public String path() {
        return path;
    }

    public String formId() {
        return formId;
    }
}
