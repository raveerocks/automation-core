package io.raveerocks.core;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    WebDriver getDriver(Capabilities capabilities);
}
