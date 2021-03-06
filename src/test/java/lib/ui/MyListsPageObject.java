package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            CLOSE_SYNC_LISTS_BUTTON,
            SWIPE_ACTION_TO_DELETE_BUTTON;

    /* TEMPLATE METHODS */
    private static String getFolderNameByXPath(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleByXPath(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_NAME}", article_title);
    }
    /* TEMPLATE METHODS */

    public MyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Open created folder in saved reading lists")
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderNameByXPath(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void closeSyncLists(){
        this.waitForElementAndClick(
                CLOSE_SYNC_LISTS_BUTTON,
                "Cannot find Close sync lists button 'Not now'",
                5);
    }

    @Step("Waiting article with expected title is present in saved reading list")
    public void waitArticleToAppear(String article_title)
    {
        String article_xpath = getSavedArticleByXPath(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                10);
    }

    @Step("Waiting article with expected title is not present in saved reading list")
    public void waitArticleToDisappear(String article_title)
    {
        String article_xpath = getSavedArticleByXPath(article_title);
        this.waitForElementNotPresented(
                article_xpath,
                "The article is still present " + article_title,
                10);
    }

    @Step("Swipe article to delete from saved reading list")
    public void swipeArticleToDelete(String article_title)
    {
        this.waitArticleToAppear(article_title);
        String article_xpath = getSavedArticleByXPath(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot delete saved article " + article_title);
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    SWIPE_ACTION_TO_DELETE_BUTTON,
                    "Cannot find and click on delete button",
                    2);
        }
        this.waitArticleToDisappear(article_title);
    }

    @Step("Open article from saved reading list")
    public void openArticleFromList(String article_name)
    {
        String article_xpath = getSavedArticleByXPath(article_name);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot find the article by title " + article_name,
                5);
    }
}
