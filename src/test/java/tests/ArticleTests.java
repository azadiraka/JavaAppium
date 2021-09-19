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

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @DisplayName("Compare article title with expected one")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We open article and make sure that title is expected")
    @Step("Starting test testCompareArticleTitle()")
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByTitle("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

//        ArticlePageObject.takeScreenshot("article_page");

        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    @DisplayName("Swipe article to the footer")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We open an article, swipe it to the footer and make sure that it has expected label")
    @Step("Starting test testSwipeArticle()")
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByTitle("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElementWithSubstring("Java");
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @DisplayName("Open article and sure that title is present")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We open an article and sure that title is present with expected substring")
    @Step("Starting test testArticleTitlePresent()")
    public void testArticleTitlePresent(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        String search_line = "iOS";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstringByDescription("Mobile operating system by Apple");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElementWithSubstring(search_line);
    }
}
