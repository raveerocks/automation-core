package io.raveerocks.services.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public final class HomePageImpl implements HomePage {

    private static final String URL = "https://www.amazon.in";
    private final WebDriver webDriver;
    private static final int EXPECTED_LIST_SIZE = 20;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchSubmit;

    @FindBy(linkText = "iOS")
    private WebElement iosFiler;

    @FindBy(xpath = "//span[@class='a-button-inner']")
    private WebElement sortDropdown;

    @FindBy(id = "s-result-sort-select_1")
    private WebElement sortOption;

    @FindBy(xpath = "//div[contains(@data-cel-widget,'search_result_')]")
    private List<WebElement> results;

    public HomePageImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public void goHome() {
        webDriver.get(URL);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 20);
        webDriverWait.until(timedDriver -> timedDriver.getTitle().contains("Amazon"));
    }

    @Override
    public WebElement getSearchBox() {
        return searchBox;
    }

    @Override
    public WebElement getSearchSubmit() {
        return searchSubmit;
    }

    @Override
    public WebElement getIosFiler() {
        return iosFiler;
    }

    @Override
    public WebElement getSortDropdown() {
        return sortDropdown;
    }

    @Override
    public WebElement getSortOption() {
        return sortOption;
    }

    @Override
    public List<AmazonItem> getResults() {
        return getMainResults()
                .stream()
                .map(this::convert)
                .filter(amazonItem -> !amazonItem.getName().equals("Not Available"))
                .collect(Collectors.toList());
    }

    private List<WebElement> getAllResults() {
        new WebDriverWait(webDriver, 30)
                .until(driver ->  results.size() > EXPECTED_LIST_SIZE);
        return results;
    }

    private List<WebElement> getMainResults() {
     return getAllResults()
             .stream()
             .filter(result -> result.getAttribute("class").contains("s-result-item"))
             .collect(Collectors.toList());
    }

    private AmazonItem convert(WebElement itemWebElement) {

        List<WebElement> name = itemWebElement.findElements(new By.ByCssSelector(".a-size-base-plus.a-color-base.a-text-normal"));
        List<WebElement> price = itemWebElement.findElements(new By.ByCssSelector(".a-price-whole"));
        List<WebElement> link = itemWebElement.findElements(new By.ByCssSelector(".a-link-normal"));

        AmazonItem amazonItem = new AmazonItem();

        if (!name.isEmpty()) {
            amazonItem.setName(name.get(0).getAttribute("innerHTML"));
        }
        if (!price.isEmpty()) {
            amazonItem.setPrice(price.get(0).getAttribute("innerHTML"));
        }
        if (!link.isEmpty()) {
            amazonItem.setLink(link.get(0).getAttribute("href"));
        }
        return amazonItem;
    }

}
