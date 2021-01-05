package io.raveerocks.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface TestService {
    WebDriver startTest(Capabilities capabilities);

    void endTest();
}
