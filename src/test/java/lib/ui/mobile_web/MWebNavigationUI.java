package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWebNavigationUI extends NavigationUI {
    static {
        SAVED_BUTTON = "id:Saved";
    }

    public MWebNavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }
}
