package io.raveerocks.util;

import io.raveerocks.driver.DefaultCapabilities;
import org.openqa.selenium.Capabilities;

public class CapabilityUtil {
    public static String getCapabilityString(Capabilities capabilities,String name){
        if (capabilities.getCapabilityNames().contains(name)){
            return capabilities.getCapability(name).toString();
        }
        return DefaultCapabilities.getCapability(name);
    }
}
