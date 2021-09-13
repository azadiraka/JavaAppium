package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        SAVED_BUTTON = "xpath://*[@content-desc='Saved']";
    }

    public AndroidNavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }
}
