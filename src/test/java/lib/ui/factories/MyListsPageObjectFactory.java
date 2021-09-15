package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidMyListsPageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.IOSMyListsPageObject;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.mobile_web.MWebMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory{
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSMyListsPageObject(driver);
        } else {
            return new MWebMyListsPageObject(driver);
        }
    }
}
