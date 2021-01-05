package io.raveerocks.driver.local;

import io.raveerocks.driver.DriverFactory;
import io.raveerocks.util.CapabilityConstants;
import io.raveerocks.util.CapabilityUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class LocalDriverFactory implements DriverFactory {
    public WebDriver getDriver(Capabilities capabilities) {
        String browserName = CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.BROWSER);
        long implicitWaitTime = (long) Double.parseDouble(CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.IMPLICIT_WAIT_TIME));
        WebDriver webDriver;
        switch (browserName) {
            case BrowserType.CHROME:
                webDriver = getChromeDriver(capabilities);
                break;
            case BrowserType.FIREFOX:
                webDriver = getFirefoxDriver(capabilities);
                break;
            case BrowserType.SAFARI:
                webDriver = getSafariDriver(capabilities);
                break;
            case BrowserType.EDGE:
                webDriver = getEdgeDriver(capabilities);
                break;
            case BrowserType.IE:
                webDriver = getInternetExplorerDriver(capabilities);
                break;
            case BrowserType.OPERA_BLINK:
                webDriver = getOperaDriver(capabilities);
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        setImplicitWaits(webDriver, implicitWaitTime);
        return webDriver;
    }

    private ChromeDriver getChromeDriver(Capabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.valueOf(CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.HEAD_LESS))) {
            chromeOptions.addArguments("-headless");
        }
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        return chromeDriver;
    }

    private FirefoxDriver getFirefoxDriver(Capabilities capabilities) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Boolean.valueOf(CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.HEAD_LESS))){
            firefoxOptions.addArguments("-headless");
        }
        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        return firefoxDriver;
    }

    private SafariDriver getSafariDriver(Capabilities capabilities) {
        SafariDriver safariDriver = new SafariDriver();
        return safariDriver;
    }

    private EdgeDriver getEdgeDriver(Capabilities capabilities) {
        System.setProperty("webdriver.edge.driver", "/usr/local/drivers/msedgedriver");
        EdgeDriver edgeDriver = new EdgeDriver();
        return edgeDriver;
    }

    private InternetExplorerDriver getInternetExplorerDriver(Capabilities capabilities) {
        InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver();
        return internetExplorerDriver;
    }

    private OperaDriver getOperaDriver(Capabilities capabilities) {
        OperaOptions operaOptions = new OperaOptions();
        OperaDriver operaDriver = new OperaDriver(operaOptions);
        return operaDriver;
    }

    private void setImplicitWaits(WebDriver webDriver, long time) {
        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

}
