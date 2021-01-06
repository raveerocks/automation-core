package io.raveerocks.core.remote.browserstack;

import java.net.MalformedURLException;
import java.net.URL;

public final class BrowserStackCredentials {

    private static final BrowserStackCredentials defaultInstance = new BrowserStackCredentials();
    private final String BROWSERSTACK_USERNAME;
    private final String BROWSERSTACK_ACCESS_KEY;
    private final String BROWSERSTACK_BUILD;
    private final String BROWSERSTACK_BUILD_NAME;
    private final String BROWSERSTACK_LOCAL;
    private final String BROWSERSTACK_LOCAL_IDENTIFIER;
    private final URL BROWSERSTACK_URL;

    private BrowserStackCredentials() {
        BROWSERSTACK_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
        BROWSERSTACK_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
        BROWSERSTACK_BUILD = System.getenv("BROWSERSTACK_BUILD");
        BROWSERSTACK_BUILD_NAME = System.getenv("BROWSERSTACK_BUILD_NAME");
        BROWSERSTACK_LOCAL = System.getenv("BROWSERSTACK_LOCAL");
        BROWSERSTACK_LOCAL_IDENTIFIER = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
        String browserstackURLString = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub.browserstack.com/wd/hub";
        URL browserstackURL;
        try {
            browserstackURL = new URL(browserstackURLString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            browserstackURL = null;
        }
        BROWSERSTACK_URL = browserstackURL;
    }

    public static BrowserStackCredentials getDefaultInstance() {
        return defaultInstance;
    }

    public String getBrowserstackUserName() {
        return BROWSERSTACK_USERNAME;
    }

    public String getBrowserstackAccessKey() {
        return BROWSERSTACK_ACCESS_KEY;
    }

    public String getBrowserstackBuild() {
        return BROWSERSTACK_BUILD;
    }

    public String getBrowserstackBuildName() {
        return BROWSERSTACK_BUILD_NAME;
    }

    public String getBrowserstackLocal() {
        return BROWSERSTACK_LOCAL;
    }

    public String getBrowserstackLocalIdentifier() {
        return BROWSERSTACK_LOCAL_IDENTIFIER;
    }

    public URL getBrowserstackURL() {
        return BROWSERSTACK_URL;
    }
}
