package io.raveerocks.services.amazon;

import io.raveerocks.services.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface HomePage extends BasePage {

    WebElement getSearchBox();
    WebElement getSearchSubmit();
    WebElement getIosFiler();
    WebElement getSortDropdown();
    WebElement getSortOption();
    List<AmazonItem> getResults();

}
