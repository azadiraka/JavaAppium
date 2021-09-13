package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
                FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']";
                ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{ARTICLE_NAME}']";
                CLOSE_SYNC_LISTS_BUTTON = "id:org.wikipedia:id/negativeButton";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
