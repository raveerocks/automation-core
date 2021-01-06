package io.raveerocks.services.amazon;


import org.openqa.selenium.WebDriver;

import java.util.List;

public final class AmazonService {

    private static final AmazonService defaultInstance = new AmazonService();
    private static final String SEARCH_TERM = "iPhoneX";

    private AmazonService() {}

    public static AmazonService getDefaultInstance() {
        return defaultInstance;
    }

    public List<AmazonItem> searchIphone(WebDriver webDriver) {
        HomePage homePage = goHome(webDriver);
        homePage.getSearchBox().sendKeys(SEARCH_TERM);
        homePage.getSearchSubmit().click();
        homePage.getIosFiler().click();
        homePage.getSortDropdown().click();
        homePage.getSortOption().click();
        return homePage.getResults();
    }

    private HomePage goHome(WebDriver webDriver) {
        HomePage homePage = new HomePageImpl(webDriver);
        homePage.goHome();
        return homePage;
    }
}
