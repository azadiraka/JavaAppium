package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "id:{SUBSTRING}";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_TO_SAVED_BUTTON = "id:Save for later";
        REMOVE_FROM_SAVED_BUTTON = "id:Saved. Activate to unsave.";
        ADD_TO_ANOTHER_LIST_BUTTON = "id:Add “Java (programming language)” to a reading list?";
        CREATE_NEW_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        NAME_OF_LIST_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_SYNC_ARTICLES_POPUP = "id:Close";
    }

    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
