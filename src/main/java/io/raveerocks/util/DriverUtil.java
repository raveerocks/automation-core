package io.raveerocks.util;

import io.raveerocks.core.TestService;
import io.raveerocks.core.local.LocalTestService;
import io.raveerocks.core.remote.RemoteTestService;
import io.raveerocks.core.remote.browserstack.BrowserStackDriverFactory;
import io.raveerocks.core.remote.saucelabs.SauceLabsTestDriverFactory;
import org.openqa.selenium.Capabilities;


public final class DriverUtil {

    private DriverUtil() {
    }

    public static TestService getDriver(Capabilities capabilities) {
        String testServiceProvider = CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.TEST_SERVICE_PROVIDER);
        TestService testService;
        switch (testServiceProvider) {
            case "local":
                testService = new LocalTestService();
                break;
            case "browser-stack":
                testService = new RemoteTestService(BrowserStackDriverFactory.getDefaultInstance());
                break;
            case "sauce-labs":
                testService = new RemoteTestService(new SauceLabsTestDriverFactory());
                break;
            default:
                throw new RuntimeException("Invalid service provider");
        }
        return testService;
    }
}
