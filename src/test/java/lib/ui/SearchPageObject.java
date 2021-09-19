package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SKIP_ONBOARDING,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL,
            SEARCH_RESULT_BY_DESC_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT,
            CLOSE_SEARCH_BUTTON,
            ARTICLE_TITLES_IN_SEARCH,
            EMPTY_SEARCH_LABEL,
            EMPTY_SEARCH_TEXT;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElementByDescription(String substring)
    {
        return SEARCH_RESULT_BY_DESC_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchWithTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */

    @Step("Click Skip onboarding button (does nothing for Mobile Web)")
    public void clickSkipOnboardingButton()
    {
        if (Platform.getInstance().isMWeb()) {
            return;
        }
        this.waitForElementAndClick(SKIP_ONBOARDING, "Cannot find and click on SKIP onboarding button", 5);
    }

    @Step("Waiting for appearing search cancel button")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Cancel search button", 5);
    }

    @Step("Waiting for disappearing search cancel button")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresented(SEARCH_CANCEL_BUTTON, "Cancel search button is still present", 5);
    }

    @Step("Click cancel search button")
    public void clickCancelSearchButton()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Cancel search button", 5);
    }

    @Step("Initializing search input")
    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking on init search element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click on search init element", 5);
    }

    @Step("Typing search line in search input")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search line", 5);
    }

    @Step("Waiting for search result by description")
    public void waitForSearchResultByDescription(String substring)
    {
        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search results with substring " + substring);
    }

    @Step("Click article that was found by substring in description")
    public void clickByArticleWithSubstringByDescription(String substring)
    {
        String search_result_xpath = getResultSearchElementByDescription(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search results with substring " + substring, 10);
    }

    public void waitForSearchResultByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search results with substring " + substring);
    }

    @Step("Waiting for search result that was found by title and description")
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_with_title_and_description = getResultSearchWithTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_with_title_and_description,
                "Cannot find search results with title '" + title + "' and description '" + description + "'",
                10);
    }

    @Step("Click article that was found by substring in title")
    public void clickByArticleWithSubstringByTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search results with substring " + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT,
                "Cannot find empty result label",
                5);
    }

    @Step("Asserting no result search")
    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT, "We didn't find any result");
    }

    @Step("Click close search button")
    public void closeSearch()
    {
        this.waitForElementAndClick(
                CLOSE_SEARCH_BUTTON,
                "Cannot find <- button",
                5);
    }

    @Step("Asserting that articles in search have expected text in title")
    public void assertArticlesInSearchHasTextInTitle(String article_name)
    {
        this.assertElementHasText(
                ARTICLE_TITLES_IN_SEARCH,
                article_name,
                "Cannot find text in titles in list with " + article_name,
                10);
    }

    @Step("Asserting that search is empty")
    public void assertEmptySearchText()
    {
        this.assertElementHasText(
                EMPTY_SEARCH_LABEL,
                EMPTY_SEARCH_TEXT,
                "Articles are still present",
                10);
    }
}
