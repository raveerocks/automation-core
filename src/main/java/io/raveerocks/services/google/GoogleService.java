package io.raveerocks.services.google;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class GoogleService {

    private static final GoogleService defaultInstance = new GoogleService();

    private GoogleService() {
    }

    public static GoogleService getDefaultInstance() { return defaultInstance;}

    private HomePage goHome(WebDriver webDriver) {
        HomePage homePage = new HomePageImpl(webDriver);
        homePage.goHome();
        return homePage;
    }

    public HomePage search(WebDriver webDriver, String searchTerm) {
        HomePage homePage = goHome(webDriver);
        homePage.getSearchBox().sendKeys(searchTerm);
        homePage.getSearchBox().sendKeys(Keys.ENTER);
        WebDriverWait webDriverWait = new WebDriverWait(homePage.getWebDriver(), 20);
        webDriverWait.until(timedDriver -> timedDriver.getTitle().contains(searchTerm));
        return homePage;
    }

}
