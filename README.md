# AI_Automation_SauceDemo_Selenium_Java

Selenium and TestNG coverage for the public search pages on https://www.expedia.com/.

Tests open the US homepage and switch the Stays, Flights, Cars, Packages, Things to do, and Cruises tabs. They check the search form that each tab shows. They do not sign in, submit a search, or pass a bot check.

`mvn test` runs the smoke suite. `mvn test -Pregression` runs smoke and the extra behavior checks. A page suite runs that page's smoke and regression tests:

```bash
mvn test -Phome
mvn test -Pflights
mvn test -Pcars
mvn test -Ppackages
mvn test -Pactivities
mvn test -Pcruises
```

The default run uses a visible Chrome window. `-Dheadless=true` is available, and Expedia may answer it with a bot check. Override the target with `-Dbase.url=` or `-Dtimeout.seconds=30`. Java 21 and Chrome are required. Selenium Manager downloads ChromeDriver.
