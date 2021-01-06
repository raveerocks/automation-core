package io.raveerocks.services.browserstack;

import io.raveerocks.util.CapabilityConstants;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class SingleTest {

    @Test
    public void test() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityConstants.TEST_SERVICE_PROVIDER, "local");
        desiredCapabilities.setCapability(CapabilityConstants.BROWSER, BrowserType.CHROME);
        AmazonSearchTest amazonSearchTest = new AmazonSearchTest(desiredCapabilities);
        amazonSearchTest.test();
    }
}
