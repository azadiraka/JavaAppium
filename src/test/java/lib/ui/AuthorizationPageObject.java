package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON ="xpath://div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name()='wpName1']",
            PASSWORD_INPUT = "css:input[name()='wpPassword1']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickAuthButton()
    {
        this.waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find Login Button",
                5);
        this.waitForElementAndClick(
                LOGIN_BUTTON,
                "Cannot find and click on Login Button",
                5);
    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find the login input and send login " + login, 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find the login input and send password ", 5);
    }

    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click on Submit button", 5);
    }
}
