# AGENTS.md

Observed 2026-11-08 on https://www.expedia.com/ (en-US, `siteid=1`). Re-check the live page before treating defaults as current.

## What

Selenium Java automation for the Expedia US homepage. The page is a search launcher. Stays is the selected product. The hero heading is "The one place you go to go places".

## Why this page behaves this way

The visible form is not the submitted state. Search posts `form#lodging_search_form`. Location, dates, travelers, and package type are copied into hidden EGDS fields. Typing a destination leaves `EGDSSearchFormLocationField-Location-destination_form_field` and `EGDSSearchFormLocationField-Selected-destination_form_field` empty until a suggestion is chosen.

Product tabs are links. Only the Stays form was reviewed.

- Stays → `/Hotels` (the form on `/`)
- Flights → `/Flights`
- Cars → `/Cars`
- Packages → `/Vacation-Packages`
- Things to do → `/Activities`
- Cruises → `/Cruises`

## Stable controls

- Destination: `#destination_form_field-input` (`data-stid="destination_form_field-menu-trigger"`). Suggestions open in a dialog titled from "Search by destination, accommodations, or landmark".
- Dates: button `data-stid="uitk-date-selector-input1-default"` opens a dialog with Calendar, Flexible dates, Done, and Apply. Values live in `EGDSDateRangePicker-StartDate-date_form_field` and `EGDSDateRangePicker-EndDate-date_form_field` as `YYYY-MM-DD`. Observed default `2026-11-09` to `2026-11-11`.
- Travelers: button `data-stid="open-room-picker"`. Observed default is 2 adults and 1 room. Confirm with `#traveler_selector_done_button`. Adult count is `EGDSSearchFormTravelersField-Adult-Room1`.
- Search: `#search_button`.
- Bundle & Save flight: `#soft_packages_flight_pill`. `EGDSSoftPackagesPackageType` stays empty until it is checked.

Prefer `data-stid` and the ids above. Generated ids (`id__…`) change between loads.

## Page chrome

- Header in this session: logo `a[data-testid="header-brand-logo-anchor"]` and Sign in at `/user/signin`.
- An OneTrust cookie banner can cover the form. Its Close button dismisses it.
- Modules under the hero (a hotel-offer band and a weekend-deals carousel) are personalized. This session received GraphQL `429` responses and stopped after those modules, with no footer. Assert the search form. Treat offers, prices, and everything below the hero as volatile.

## How

The repo has no build yet. Add the Maven or Gradle commands here once they exist. Confirm a change on the live homepage: dismiss the cookie banner, choose a suggestion, then read the hidden fields before submit.

## Boundaries

- Stay on public search and read-only pages. Leave sign-in, payment, and booking alone.
- Leave bot, rate-limit, and consent controls alone. A GraphQL `429` means retry later.
- Keep this file short. Leave volatile copy, prices, and generated ids out of it.
