package io.raveerocks.driver.remote;

import io.raveerocks.util.CapabilityConstants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriverFactory implements RemoteDriverFactory {

    BrowserStackCredentials browserStackCredentials;

    public BrowserStackDriverFactory() {
        browserStackCredentials = new BrowserStackCredentials();
    }

    @Override
    public WebDriver getDriver(Capabilities capabilities) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("browserstack.geoLocation", "US");
            desiredCapabilities.setCapability(CapabilityConstants.BUILD,browserStackCredentials.getBROWSERSTACK_BUILD());
            System.out.println("---> "+browserStackCredentials.getBROWSERSTACK_BUILD());
            capabilities.merge(desiredCapabilities);
            return new RemoteWebDriver(new URL(browserStackCredentials.getBROWSERSTACK_URL()), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("REMOTE URL ERROR");
    }
}
