package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.ValidationEventLocator;

@Epic("Tests for reading lists")
public class MyListsTests extends CoreTestCase
{
    private static String
            name_of_folder = "Learning programming",
            first_search_line = "Harry Potter",
            second_search_line = "Star Wars",
            login = "azadiraka",
            password = "javaappium";

    @Test
    @DisplayName("Saving one article in saved reading list")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "My lists"), @Feature(value = "Authorization")})
    @Description("We open arcicle and add it to saved reading list (for mobile apps we created a new reading list '{name_of_folder}')")
    @Step("Starting test testSaveFirstArticleInMyList()")
    public void testSaveFirstArticleInMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstringByTitle("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElementWithSubstring("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if (Platform.getInstance().isMWeb())
        {
            ArticlePageObject.addArticlesToSaved();

            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElementWithSubstring("Java (programming language)");

            Assert.assertEquals("We are not on the same article",
                    article_title,
                    ArticlePageObject.getArticleTitle("Java (programming language)"));

            ArticlePageObject.addArticlesToSaved();
        } else {
            ArticlePageObject.addToMyListAndCreateNewList(name_of_folder);
        }

        ArticlePageObject.closeArticle();
        SearchPageObject.closeSearch();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickSaved();

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncArticlesPopupIOSApp();
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    @DisplayName("Saving two articles in saved reading list")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "My lists"), @Feature(value = "Authorization")})
    @Description("We two arcicles '{first_search_line}' and '{second_search_line}' and add it to saved reading list (for mobile apps we created a new reading list)")
    @Step("Starting test testAddTwoArticlesToSaved()")
    public void testAddTwoArticlesToSaved() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipOnboardingButton();
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine(first_search_line);
        SearchPageObject.clickByArticleWithSubstringByTitle(first_search_line);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElementWithSubstring(first_search_line);
        String first_article_title = ArticlePageObject.getArticleTitle(first_search_line);

        if (Platform.getInstance().isMWeb())
        {
            ArticlePageObject.addArticlesToSaved();

            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElementWithSubstring(first_search_line);

            Assert.assertEquals("We are not on the same article",
                    first_article_title,
                    ArticlePageObject.getArticleTitle(first_search_line));

            ArticlePageObject.addArticlesToSaved();
        } else {
            ArticlePageObject.addArticlesToSaved();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearchButton();

        SearchPageObject.typeSearchLine(second_search_line);
        SearchPageObject.clickByArticleWithSubstringByTitle(second_search_line + " (film)");

        ArticlePageObject.waitForTitleElementWithSubstring(second_search_line + " (film)");
        String second_article_title = ArticlePageObject.getArticleTitle(second_search_line + " (film)");

        if (Platform.getInstance().isMWeb())
        {
            ArticlePageObject.addArticlesToSaved();

            ArticlePageObject.waitForTitleElementWithSubstring(second_search_line + " (film)");

            Assert.assertEquals("We are not on the same article",
                    second_article_title,
                    ArticlePageObject.getArticleTitle(second_search_line + " (film)"));

            ArticlePageObject.addArticlesToSaved();
        } else {
            ArticlePageObject.addArticlesToSaved();
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.closeSearch();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickSaved();

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncArticlesPopupIOSApp();
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        MyListPageObject.waitArticleToAppear(first_search_line);
        MyListPageObject.waitArticleToAppear(second_search_line + " (film)");

       if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
           MyListPageObject.swipeArticleToDelete(second_search_line + " (film)");
       }

        MyListPageObject.openArticleFromList(first_search_line);

        String article_name = ArticlePageObject.getArticleTitle(first_search_line);
        ArticlePageObject.assertArticleHasATextInTitle(article_name);

        ArticlePageObject.waitForUnsavedButton();
    }
}
