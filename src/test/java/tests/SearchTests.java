package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @DisplayName("Waiting for search result")
    @Features(value = {@Feature(value = "Search")})
    @Description("Waiting for search result that has expected substring")
    @Step("Starting test testSearch()")
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultByDescription("bject-oriented programming language");
    }

    @Test
    @DisplayName("Cancel search")
    @Features(value = {@Feature(value = "Search")})
    @Description("Typing text is search input and then cancel search")
    @Step("Starting test testCancelSearch()")
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @DisplayName("Amount of non empty search")
    @Features(value = {@Feature(value = "Search")})
    @Description("Make query by '{search_line}' and make sure that search is not empty")
    @Step("Starting test testAmountOfNonEmptySearch()")
    public void testAmountOfNonEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    @DisplayName("Validation three search results by title and description")
    @Features(value = {@Feature(value = "Search")})
    @Description("Make query by 'MacBook' and make sure that three search results have expected title and description")
    @Step("Starting test testAmountOfSearchByNameAndDescription()")
    public void testAmountOfSearchByNameAndDescription(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("MacBook");
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "There are not 3 search results at least",
                amount_of_search_results >= 3);

        SearchPageObject.waitForElementByTitleAndDescription("MacBook Pro", "Line of notebook computers");
        SearchPageObject.waitForElementByTitleAndDescription("MacBook", "Lines of Apple notebook computers");
        SearchPageObject.waitForElementByTitleAndDescription("MacBook Air", "Line of ultraportable notebook computers by Apple");
    }

    @Test
    @DisplayName("Empty search")
    @Features(value = {@Feature(value = "Search")})
    @Description("Make query by '{search_line}' and make sure that search result is empty")
    @Step("Starting test testAmountOfEmptySearch()")
    public void testAmountOfEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "sdjfnsdndfvjb";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultSearch();
    }

    @Test
    @DisplayName("Compare article title with expected one after background")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We open article and make sure that title is expected after background")
    @Step("Starting test testElementPresentAfterBackground()")
    public void testElementPresentAfterBackground(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultByDescription("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.getArticleTitle("Java (programming language)");
        this.backgroundApp(2);
        ArticlePageObject.getArticleTitle("Java (programming language)");
    }

    @Test
    @DisplayName("Compare article title with expected one after background")
    @Features(value = {@Feature(value = "Search")})
    @Description("Make query by '{search_line}' and make sure that all search result have text '{search_line}' and then clear search")
    @Step("Starting test testCancelArticleSearch()")
    public void testCancelArticleSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "Android";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertArticlesInSearchHasTextInTitle(search_line);
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.assertEmptySearchText();
    }
}
