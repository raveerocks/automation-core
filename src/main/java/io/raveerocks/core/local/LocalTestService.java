package io.raveerocks.core.local;

import io.raveerocks.core.TestService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public final class LocalTestService implements TestService {

    private final LocalDriverFactory localDriverFactory;
    private WebDriver webDriver;

    public LocalTestService() {
        localDriverFactory = LocalDriverFactory.getDefaultInstance();
    }

    @Override
    public WebDriver startTest(Capabilities capabilities) {
        webDriver = localDriverFactory.getDriver(capabilities);
        return webDriver;
    }

    @Override
    public void endTest() {
        webDriver.quit();
    }
}
