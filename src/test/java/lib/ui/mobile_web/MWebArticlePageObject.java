package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWebArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        ADD_TO_SAVED_BUTTON = "css:#page-actions a#ca-watch button";
        REMOVE_FROM_SAVED_BUTTON = "id:Saved. Activate to unsave.";
        ADD_TO_ANOTHER_LIST_BUTTON = "id:Add “Java (programming language)” to a reading list?";
        CREATE_NEW_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        NAME_OF_LIST_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_SYNC_ARTICLES_POPUP = "id:Close";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions a#ca-watch.watched button";
    }

    public MWebArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
