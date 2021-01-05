package io.raveerocks.google;

import io.raveerocks.pages.BasePage;
import org.openqa.selenium.WebElement;

public interface HomePage extends BasePage {
    WebElement getSearchBox();

    WebElement getSubmit();

    String getTitle();
}
