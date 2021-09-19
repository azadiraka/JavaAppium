package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE_TPL,
            FOOTER_ELEMENT,
            ADD_TO_SAVED_BUTTON,
            REMOVE_FROM_SAVED_BUTTON,
            ADD_TO_ANOTHER_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            NAME_OF_LIST_INPUT,
            OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_SYNC_ARTICLES_POPUP;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getArticleTitleWithSubstring(String substring)
    {
        return TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    @Step("Waiting that title '{article_title}' has expected substring in title")
    public WebElement waitForTitleElementWithSubstring(String substring)
    {
        String article_title = getArticleTitleWithSubstring(substring);
        return this.waitForElementPresent(article_title, "Cannot find the title of article. The title is " + substring, 5);
    }

    @Step("Getting article title")
    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElementWithSubstring(substring);
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping article to the footer")
    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find footer element",
                    40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find footer element",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find footer element",
                    40);
        }
    }

    @Step("Creating new reading list and adding article in it")
    public void addToMyListAndCreateNewList(String name_of_folder)
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
                    "Cannot find bookmark button",
                    5);
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
                    "Cannot find bookmark button",
                    5);
            this.waitForElementAndClick(
                    ADD_TO_ANOTHER_LIST_BUTTON,
                    "Cannot find 'Add to another reading list' button",
                    5);
            this.waitForElementAndClick(
                    CREATE_NEW_LIST_BUTTON,
                    "Cannot find 'Create new' button",
                    5);
            this.waitForElementAndSendKeys(
                    NAME_OF_LIST_INPUT,
                    name_of_folder,
                    "Cannot find 'Name of this list' input",
                    2);
            this.waitForElementAndClick(
                    OK_BUTTON,
                    "Cannot find 'OK' button",
                    2);
        } else {
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
                    "Cannot find bookmark button",
                    5);
            this.waitForElementAndClick(
                    ADD_TO_ANOTHER_LIST_BUTTON,
                    "Cannot find 'Add to another reading list' button",
                    5);
            this.waitForElementAndClick(
                    CREATE_NEW_LIST_BUTTON,
                    "Cannot find 'Create new' button",
                    5);
            this.waitForElementAndSendKeys(
                    NAME_OF_LIST_INPUT,
                    name_of_folder,
                    "Cannot find 'Name of this list' input",
                    2);
            this.waitForElementAndClick(
                    OK_BUTTON,
                    "Cannot find 'OK' button",
                    2);
        }
    }

    @Step("Adding article to Saved reading list")
    public void addArticlesToSaved()
    {
        if (Platform.getInstance().isMWeb()) {
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
                    "Cannot find bookmark button",
                    30);
        } else {
            this.waitForElementAndClick(
                    ADD_TO_SAVED_BUTTON,
                    "Cannot find bookmark button",
                    5);
        }
    }

    @Step("Waiting for 'Remove from saved' button")
    public void waitForUnsavedButton()
    {
        this.waitForElementPresent(
                REMOVE_FROM_SAVED_BUTTON,
                "Perhaps this article was not added to Saved list",
                5);
    }

    @Step("Closing article")
    public void closeArticle()
    {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid())
        {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot find <- button",
                    1);
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Asserting that title has expected text in title")
    public void assertArticleHasATextInTitle(String article_name)
    {
        String article_xpath = getArticleTitleWithSubstring(article_name);
        this.assertElementHasText(
                article_xpath,
                article_name,
                "Cannot find expected text in first article found by the request " + article_name,
                5);
    }

    @Step("Closing Sync article popup in iOS app (does not work for Android and Mobile Web)")
    public void closeSyncArticlesPopupIOSApp()
    {
        this.waitForElementAndClick(
                CLOSE_SYNC_ARTICLES_POPUP,
                "Cannot find and close sync articles popup",
                5);
    }

    public void removeArticleFromSavedIfItWasAdded()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON))
        {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot find and remove an article from Saved", 2);
            this.waitForElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot find the button to add an article to Saved after removing ot from Saved", 3);
        }
    }
}
