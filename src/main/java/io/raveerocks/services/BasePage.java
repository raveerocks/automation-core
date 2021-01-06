package io.raveerocks.services;

import org.openqa.selenium.WebDriver;

public interface BasePage {
    WebDriver getWebDriver();
    void goHome();
}
