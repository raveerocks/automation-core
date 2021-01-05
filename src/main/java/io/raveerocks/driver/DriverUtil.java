package io.raveerocks.driver;

import io.raveerocks.driver.local.LocalTestService;
import io.raveerocks.driver.remote.BrowserStackDriverFactory;
import io.raveerocks.driver.remote.RemoteTestService;
import io.raveerocks.driver.remote.SauceLabsTestDriverFactory;
import io.raveerocks.util.CapabilityConstants;
import io.raveerocks.util.CapabilityUtil;
import org.openqa.selenium.Capabilities;



public class DriverUtil {
    public static TestService getDriver(Capabilities capabilities){
        String testServiceProvider = CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.TEST_SERVICE_PROVIDER);
        TestService testService;
        switch (testServiceProvider){
            case "local": testService = new LocalTestService();break;
            case "browser-stack": testService = new RemoteTestService(new BrowserStackDriverFactory());break;
            case "sauce-labs":testService = new RemoteTestService(new SauceLabsTestDriverFactory());break;
            default:throw new RuntimeException("Invalid service provider");
        }
        return testService;
    }
}
