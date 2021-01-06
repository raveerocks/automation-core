package io.raveerocks.services.browserstack;

import io.raveerocks.core.AbstractTest;
import io.raveerocks.core.TestService;
import io.raveerocks.services.amazon.AmazonItem;
import io.raveerocks.services.amazon.AmazonService;
import io.raveerocks.util.DriverUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class AmazonSearchTest extends AbstractTest {

    private Capabilities capabilities;


    public AmazonSearchTest(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    @Override
    protected void test() {
        TestService testService = DriverUtil.getDriver(capabilities);
        WebDriver webDriver = testService.startTest(capabilities);
        List<AmazonItem> items = AmazonService.getDefaultInstance().searchIphone(webDriver);
        items.stream().forEach(System.out::println);
        testService.endTest();
    }
}
