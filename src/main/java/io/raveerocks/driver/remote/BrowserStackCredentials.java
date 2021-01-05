package io.raveerocks.driver.remote;

public class BrowserStackCredentials {

    private final String BROWSERSTACK_USERNAME;
    private final String BROWSERSTACK_ACCESS_KEY;
    private final String BROWSERSTACK_BUILD;
    private final String BROWSERSTACK_BUILD_NAME;
    private final String BROWSERSTACK_LOCAL;
    private final String BROWSERSTACK_LOCAL_IDENTIFIER;
    private final String BROWSERSTACK_URL;

    public BrowserStackCredentials() {
        BROWSERSTACK_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
        BROWSERSTACK_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
        BROWSERSTACK_BUILD = System.getenv("BROWSERSTACK_BUILD");
        BROWSERSTACK_BUILD_NAME = System.getenv("BROWSERSTACK_BUILD_NAME");
        BROWSERSTACK_LOCAL = System.getenv("BROWSERSTACK_LOCAL");
        BROWSERSTACK_LOCAL_IDENTIFIER = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
        BROWSERSTACK_URL = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub.browserstack.com/wd/hub";
    }


    public String getBROWSERSTACK_USERNAME() {
        return BROWSERSTACK_USERNAME;
    }

    public String getBROWSERSTACK_ACCESS_KEY() {
        return BROWSERSTACK_ACCESS_KEY;
    }

    public String getBROWSERSTACK_BUILD() {
        return BROWSERSTACK_BUILD;
    }

    public String getBROWSERSTACK_BUILD_NAME() {
        return BROWSERSTACK_BUILD_NAME;
    }

    public String getBROWSERSTACK_LOCAL() {
        return BROWSERSTACK_LOCAL;
    }

    public String getBROWSERSTACK_LOCAL_IDENTIFIER() {
        return BROWSERSTACK_LOCAL_IDENTIFIER;
    }

    public String getBROWSERSTACK_URL() {
        return BROWSERSTACK_URL;
    }
}
