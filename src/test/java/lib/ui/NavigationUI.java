package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            SAVED_BUTTON,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMWeb())
        {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click on navigation menu",
                    5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickSaved()
    {
        if (Platform.getInstance().isMWeb())
        {
            this.tryClickElementWithFewAttempts(
                    SAVED_BUTTON,
                    "Cannot find Saved button",
                    5);
        }
        this.waitForElementAndClick(
                SAVED_BUTTON,
                "Cannot find Saved button",
                1);
    }
}
