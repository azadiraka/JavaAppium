package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWebSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_BY_DESC_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                "xpath://div/ul/li[@title='{TITLE}']" +
                        "[.//div[contains(@class, 'wikidata-description')][contains(text(), '{DESCRIPTION}')]]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT = "id:No results found";
        CLOSE_SEARCH_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        ARTICLE_TITLES_IN_SEARCH = "id:org.wikipedia:id/page_list_item_title";
        EMPTY_SEARCH_LABEL = "css:p.without-results";
        EMPTY_SEARCH_TEXT = "id:Search Wikipedia in more languages";
    }

    public MWebSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
