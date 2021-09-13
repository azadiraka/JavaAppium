package lib.ui.factories;

import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSSearchPageObject;
import lib.ui.mobile_web.MWebSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSSearchPageObject(driver);
        } else {
            return new MWebSearchPageObject(driver);
        }
    }
}
