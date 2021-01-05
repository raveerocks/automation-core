package io.raveerocks.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageImpl implements HomePage {

    private static final String URL = "https://www.google.com";
    private WebDriver webDriver;
    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = "(//input[@value='Google Search'])[2]")
    private WebElement submit;


    /*

    public HomePageImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @Override
    public void goHome() {
        webDriver.get(URL);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,20);
        webDriverWait.until(timedDriver ->timedDriver.getTitle().contains("Google"));
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public WebElement getSearchBox() {
        return searchBox;
    }

    @Override
    public WebElement getSubmit() {
        return submit;
    }
     */

    public HomePageImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public WebElement getSearchBox() {
        return searchBox;
    }

    @Override
    public WebElement getSubmit() {
        return submit;
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public void goHome() {
        webDriver.get(URL);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 20);
        webDriverWait.until(timedDriver -> timedDriver.getTitle().contains("Google"));
    }
}
