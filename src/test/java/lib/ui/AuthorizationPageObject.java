package lib.ui;

import io.qameta.allure.Step;
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

    @Step("Click om Authorization button - Log in")
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

    @Step("Enter login and password in inputs")
    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find the login input and send login " + login, 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find the login input and send password ", 5);
    }

    @Step("Click on submit authorization")
    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click on Submit button", 5);
    }
}
