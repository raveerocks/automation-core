package io.raveerocks.util;

import org.openqa.selenium.Capabilities;

public final class CapabilityUtil {

    private CapabilityUtil(){}

    public static String getCapabilityString(Capabilities capabilities, String name) {
        if (capabilities.getCapabilityNames().contains(name)) {
            return capabilities.getCapability(name).toString();
        }
        return DefaultCapabilities.getCapability(name);
    }
}
