package io.raveerocks.services.google;

import io.raveerocks.services.BasePage;
import org.openqa.selenium.WebElement;

public interface HomePage extends BasePage {

    WebElement getSearchBox();
    String getTitle();

}
