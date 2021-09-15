package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWebMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "id:{FOLDER_NAME}";
        ARTICLE_BY_TITLE_TPL = "id:{ARTICLE_NAME}";
        CLOSE_SYNC_LISTS_BUTTON = "id:org.wikipedia:id/negativeButton";
        SWIPE_ACTION_TO_DELETE_BUTTON = "id:swipe action delete";
    }

    public MWebMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
