package io.raveerocks.driver.local;

import io.raveerocks.driver.TestService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class LocalTestService implements TestService {

    private LocalDriverFactory localDriverFactory;
    private WebDriver webDriver;

    public LocalTestService() {
        localDriverFactory = new LocalDriverFactory();
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
