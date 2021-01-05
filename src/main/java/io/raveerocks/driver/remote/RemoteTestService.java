package io.raveerocks.driver.remote;

import io.raveerocks.driver.TestService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class RemoteTestService implements TestService {

    private RemoteDriverFactory remoteFactory;
    private WebDriver webDriver;

    public RemoteTestService(RemoteDriverFactory remoteFactory) {
        this.remoteFactory = remoteFactory;
    }

    @Override
    public WebDriver startTest(Capabilities capabilities) {
        webDriver = remoteFactory.getDriver(capabilities);
        return webDriver;
    }

    @Override
    public void endTest() {
        webDriver.quit();
    }
}
