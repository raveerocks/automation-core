package io.raveerocks;

import io.raveerocks.services.GoogleService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class TestRemote {

    @Test
    public void test(){
        GoogleService googleService =  GoogleService.getDefaultInstance();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest-beta");
        caps.setCapability("os", "Windows");
        caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
        caps.setCapability("build", "BStack Build Number 1");
      //  HomePage homePage = googleService.searchByEnter(caps, "Browser Stack");
        //Assert.assertEquals(homePage.getWebDriver().getTitle(),"Browser Stack - Google Search");
        //homePage.getWebDriver().quit();
    }
}
