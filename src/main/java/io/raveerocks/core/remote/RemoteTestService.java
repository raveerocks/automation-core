package io.raveerocks.core.remote;

import io.raveerocks.core.DriverFactory;
import io.raveerocks.core.TestService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public final class RemoteTestService implements TestService {

    private final DriverFactory remoteFactory;
    private WebDriver webDriver;

    public RemoteTestService(DriverFactory remoteFactory) {
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
