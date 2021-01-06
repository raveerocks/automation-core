package io.raveerocks.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class CapabilityBuilder {

    private String testServiceProvider;
    private String operatingSystem;
    private String operatingSystemVersion;
    private String resolution;
    private String browser;
    private String browserVersion;
    private String name;
    private String build;
    private String implicitWaitTime;
    private String headLess;

    public CapabilityBuilder setTestServiceProvider(String testServiceProvider) {
        this.testServiceProvider = testServiceProvider;
        return this;
    }

    public CapabilityBuilder setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public CapabilityBuilder setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
        return this;
    }

    public CapabilityBuilder setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public CapabilityBuilder setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public CapabilityBuilder setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
        return this;
    }

    public CapabilityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CapabilityBuilder setBuild(String build) {
        this.build = build;
        return this;
    }

    public CapabilityBuilder setImplicitWaitTime(String implicitWaitTime) {
        this.implicitWaitTime = implicitWaitTime;
        return this;
    }

    public CapabilityBuilder setHeadLess(String headLess) {
        this.headLess = headLess;
        return this;
    }

    public Capabilities build() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityConstants.TEST_SERVICE_PROVIDER, testServiceProvider);
        capabilities.setCapability(CapabilityConstants.OPERATING_SYSTEM, operatingSystem);
        capabilities.setCapability(CapabilityConstants.OPERATING_SYSTEM_VERSION, operatingSystemVersion);
        capabilities.setCapability(CapabilityConstants.RESOLUTION, resolution);
        capabilities.setCapability(CapabilityConstants.BROWSER, browser);
        capabilities.setCapability(CapabilityConstants.BROWSER_VERSION, browserVersion);
        capabilities.setCapability(CapabilityConstants.NAME, name);
        capabilities.setCapability(CapabilityConstants.BUILD, build);
        capabilities.setCapability(CapabilityConstants.IMPLICIT_WAIT_TIME, implicitWaitTime);
        capabilities.setCapability(CapabilityConstants.HEAD_LESS, headLess);
        return capabilities;
    }
}
