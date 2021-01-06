package io.raveerocks.services.google;

import io.raveerocks.core.AbstractTest;
import io.raveerocks.core.TestService;
import io.raveerocks.util.DriverUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class GoogleSearchTest extends AbstractTest {

    private Capabilities capabilities;
    private String searchTerm;
    private String expectedTitle;

    public GoogleSearchTest(Capabilities capabilities, String searchTerm, String expectedTitle) {
        this.capabilities = capabilities;
        this.searchTerm = searchTerm;
        this.expectedTitle = expectedTitle;
    }

    @Override
    protected void test() {
        TestService testService = DriverUtil.getDriver(capabilities);
        WebDriver webDriver = testService.startTest(capabilities);
        HomePage homePage = GoogleService.getDefaultInstance().search(webDriver, searchTerm);
        Assert.assertEquals(homePage.getTitle(), expectedTitle);
        testService.endTest();
        homePage.getWebDriver().quit();
    }
}
