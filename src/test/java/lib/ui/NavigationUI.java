package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            SAVED_BUTTON;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickSaved()
    {
        this.waitForElementAndClick(
                SAVED_BUTTON,
                "Cannot find Saved button",
                1);
    }
}
