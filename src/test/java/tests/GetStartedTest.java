package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMWeb())) {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditPreferredLanguagesLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }

}
