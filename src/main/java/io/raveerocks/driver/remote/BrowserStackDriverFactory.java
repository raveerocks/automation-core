package io.raveerocks.driver.remote;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriverFactory implements RemoteDriverFactory {

    private String remoteURL;

    public BrowserStackDriverFactory() {
        this.remoteURL = System.getenv("BS_URL");;
    }


    @Override
    public WebDriver getDriver(Capabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(remoteURL),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("REMOTE URL ERROR");
    }
}
