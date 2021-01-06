package io.raveerocks.services.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class HomePageImpl implements HomePage {

    private static final String URL = "https://www.google.co.in";
    private final WebDriver webDriver;

    @FindBy(name = "q")
    private WebElement searchBox;


    public HomePageImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public void goHome() {
        webDriver.get(URL);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 20);
        webDriverWait.until(timedDriver -> timedDriver.getTitle().contains("Google"));
        if (webDriver.findElements(new By.ByLinkText("English")).size() != 0) {
            webDriver.findElement(new By.ByLinkText("English")).click();
        }
        webDriverWait.until(timedDriver -> timedDriver.getTitle().contains("Google"));
    }

    @Override
    public WebElement getSearchBox() {
        return searchBox;
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

}
