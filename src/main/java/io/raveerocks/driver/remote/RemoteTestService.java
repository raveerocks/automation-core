package io.raveerocks.driver.remote;

import io.raveerocks.driver.TestService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
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
        if (remoteFactory instanceof BrowserStackDriverFactory){
            JavascriptExecutor jse = (JavascriptExecutor)webDriver;
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
        }
        webDriver.quit();
    }
}
