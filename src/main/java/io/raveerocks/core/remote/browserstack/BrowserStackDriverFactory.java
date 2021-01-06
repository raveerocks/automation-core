package io.raveerocks.core.remote.browserstack;

import io.raveerocks.core.DriverFactory;
import io.raveerocks.util.CapabilityConstants;
import io.raveerocks.util.CapabilityUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;


public final class BrowserStackDriverFactory implements DriverFactory {

    private static final BrowserStackDriverFactory defaultInstance = new BrowserStackDriverFactory();
    private final BrowserStackCredentials browserStackCredentials;

    private BrowserStackDriverFactory() {
        browserStackCredentials = BrowserStackCredentials.getDefaultInstance();
    }

    public static BrowserStackDriverFactory getDefaultInstance() {
        return defaultInstance;
    }

    @Override
    public WebDriver getDriver(Capabilities capabilities) {
        long implicitWaitTime = (long) Double.parseDouble(CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.IMPLICIT_WAIT_TIME));
        preProcess(capabilities);
        WebDriver driver = new RemoteWebDriver(browserStackCredentials.getBrowserstackURL(), capabilities);
        postProcess(driver, implicitWaitTime);
        return driver;
    }

    private void preProcess(Capabilities capabilities) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserstack.geoLocation", "US");
        desiredCapabilities.setCapability(CapabilityConstants.BUILD, browserStackCredentials.getBrowserstackBuild());
        capabilities.merge(desiredCapabilities);
    }

    private void postProcess(WebDriver webDriver, long time) {
        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
