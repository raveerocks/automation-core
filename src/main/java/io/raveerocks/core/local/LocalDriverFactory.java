package io.raveerocks.core.local;

import io.raveerocks.core.DriverFactory;
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

public final class LocalDriverFactory implements DriverFactory {

    private static final LocalDriverFactory defaultInstance = new LocalDriverFactory();

    private LocalDriverFactory() {
    }

    public static LocalDriverFactory getDefaultInstance() {
        return defaultInstance;
    }

    @Override
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
                webDriver = getSafariDriver();
                break;
            case BrowserType.EDGE:
                webDriver = getEdgeDriver();
                break;
            case BrowserType.IE:
                webDriver = getInternetExplorerDriver();
                break;
            case BrowserType.OPERA_BLINK:
                webDriver = getOperaDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        setImplicitWaits(webDriver, implicitWaitTime);
        return webDriver;
    }

    private ChromeDriver getChromeDriver(Capabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.HEAD_LESS))) {
            chromeOptions.addArguments("-headless");
        }
        return new ChromeDriver(chromeOptions);
    }

    private FirefoxDriver getFirefoxDriver(Capabilities capabilities) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(CapabilityUtil.getCapabilityString(capabilities, CapabilityConstants.HEAD_LESS))) {
            firefoxOptions.addArguments("-headless");
        }
        return new FirefoxDriver(firefoxOptions);
    }

    private SafariDriver getSafariDriver() {
        return new SafariDriver();
    }

    private EdgeDriver getEdgeDriver() {
        System.setProperty("webdriver.edge.driver", "/usr/local/drivers/msedgedriver");
        return new EdgeDriver();
    }

    private InternetExplorerDriver getInternetExplorerDriver() {
        return new InternetExplorerDriver();
    }

    private OperaDriver getOperaDriver() {
        OperaOptions operaOptions = new OperaOptions();
        return new OperaDriver(operaOptions);
    }

    private void setImplicitWaits(WebDriver webDriver, long time) {
        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
