package io.raveerocks.driver;

import io.raveerocks.util.CapabilityConstants;

import java.util.HashMap;
import java.util.Map;

public class DefaultCapabilities {

    private static Map<String,String>  defaultCapabilities;

    static {
        defaultCapabilities = new HashMap<>();
        defaultCapabilities.put(CapabilityConstants.TEST_SERVICE_PROVIDER,"Local");
        defaultCapabilities.put(CapabilityConstants.IMPLICIT_WAIT_TIME,"30");
        defaultCapabilities.put(CapabilityConstants.HEAD_LESS,"false");
    }

    public static String getCapability(String capabilityName){
        if (!defaultCapabilities.containsKey(capabilityName)){
            throw new RuntimeException(capabilityName + " has not default value set");
        }
        return defaultCapabilities.get(capabilityName);
    }

}
