package io.raveerocks.util;

public class TestCase {

    private String testServiceProvider;
    private String operatingSystem;
    private String operatingSystemVersion;
    private String resolution;
    private String browser;
    private String browserVersion;
    private String name;
    private String build;
    private String[] params;
    private String expectedResult;
    private String implicitWaitTime;
    private String headLess;

    public String getTestServiceProvider() {
        return testServiceProvider;
    }

    public void setTestServiceProvider(String testServiceProvider) {
        this.testServiceProvider = testServiceProvider;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getImplicitWaitTime() {
        return implicitWaitTime;
    }

    public void setImplicitWaitTime(String implicitWaitTime) {
        this.implicitWaitTime = implicitWaitTime;
    }

    public String getHeadLess() {
        return headLess;
    }

    public void setHeadLess(String headLess) {
        this.headLess = headLess;
    }
}
