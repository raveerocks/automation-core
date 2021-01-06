package io.raveerocks.util;

import java.util.HashMap;
import java.util.Map;

public final class DefaultCapabilities {

    private static final Map<String, String> defaultCapabilities;

    private DefaultCapabilities(){}

    static {
        defaultCapabilities = new HashMap<>();
        defaultCapabilities.put(CapabilityConstants.TEST_SERVICE_PROVIDER, "Local");
        defaultCapabilities.put(CapabilityConstants.IMPLICIT_WAIT_TIME, "30");
        defaultCapabilities.put(CapabilityConstants.HEAD_LESS, "false");
    }

    public static String getCapability(String capabilityName) {
        if (!defaultCapabilities.containsKey(capabilityName)) {
            throw new RuntimeException(capabilityName + " has not default value set");
        }
        return defaultCapabilities.get(capabilityName);
    }

}
