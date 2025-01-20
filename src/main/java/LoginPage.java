import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    Locator textHeaderLogin;
    Locator inputUsername;
    Locator inputPassword;
    Locator buttonLogin;
    Locator iconErrorUsername;
    Locator iconErrorPassword;
    Locator textHeaderError;
    Locator buttonError;
    Locator iconButtonError;

    public LoginPage(Page page){
        this.textHeaderLogin= page.locator("xpath=//div[@class=\"login_logo\"]");
        this.inputUsername= page.locator("xpath=//input[@data-test=\"username\"]");
        this.inputPassword= page.locator("xpath=//input[@data-test=\"password\"]");
        this.buttonLogin= page.locator("xpath=//input[@data-test=\"login-button\"]");
        this.iconErrorUsername= page.locator("xpath=//input[@data-test=\"username\"]/following-sibling::*[name()=\"svg\"][contains(@class, \"error_icon\")]");
        this.iconErrorPassword= page.locator("xpath=//input[@data-test=\"password\"]/following-sibling::*[name()=\"svg\"][contains(@class, \"error_icon\")]");
        this.textHeaderError= page.locator("xpath=//h3[@data-test=\"error\"]");
        this.buttonError= page.locator("xpath=//button[@data-test=\"error-button\"]");
        this.iconButtonError= page.locator("xpath=//button[@data-test=\"error-button\"]/*[name()=\"svg\"]");
    }

    @Step("Verify Login Page opened OK")
    public void verifyLoginPageOK(){
        assertThat(textHeaderLogin).isVisible();
        assertThat(inputUsername).isVisible();
        assertThat(inputPassword).isVisible();
        assertThat(buttonLogin).isVisible();
        assertThat(textHeaderLogin).hasText("Swag Labs");
        assertThat(inputUsername).hasAttribute("placeholder", "Username");
        assertThat(inputPassword).hasAttribute("placeholder", "Password");
        assertThat(buttonLogin).hasAttribute("value", "Login");
    }

    @Step("Verify Login Page opened OK but the Test Case is failed")
    public void verifyLoginPageFailed(){
        assertThat(textHeaderLogin).isVisible();
        assertThat(inputUsername).isVisible();
        assertThat(inputPassword).isVisible();
        assertThat(buttonLogin).isVisible();
        assertThat(textHeaderLogin).hasText("Swag Labs");
        assertThat(inputUsername).hasAttribute("placeholder", "Username");
        assertThat(inputPassword).hasAttribute("placeholder", "Password");
        assertThat(buttonLogin).hasAttribute("value", "Logins");
    }

    @Step("Click Login Button")
    public void clickLoginButton(){
        buttonLogin.click();
    }

    @Step("Verify error on field Username")
    public void verifyErrorOnUsername(){
        assertThat(iconErrorUsername).isVisible();
    }

    @Step("Verify error on field Password")
    public void verifyErrorOnPassword(){
        assertThat(iconErrorPassword).isVisible();
    }

    @Step("Verify error \"Username is required\"")
    public void verifyErrorUsernameIsRequired(){
        assertThat(textHeaderError).isVisible();
        assertThat(buttonError).isVisible();
        assertThat(iconButtonError).isVisible();
        assertThat(textHeaderError).hasText("Epic sadface: Username is required");
    }

    @Step("Input into Username field")
    public void inputToUsername(String text){
        inputUsername.clear();
        inputUsername.fill(text);
    }

    @Step("Input into Password field")
    public void inputToPassword(String text){
        inputPassword.clear();
        inputPassword.fill(text);
    }

    @Step("Verify error \"Username and Password do not match\"")
    public void verifyErrorUsernameAndPasswordDoNotMatch(){
        assertThat(textHeaderError).isVisible();
        assertThat(buttonError).isVisible();
        assertThat(iconButtonError).isVisible();
        assertThat(textHeaderError).hasText("Epic sadface: Username and password do not match any user in this service");
    }

    @Step("Verify Login Page is not shown anymore")
    public void verifyLoginPageIsNotShownAnymore(){
        assertThat(textHeaderLogin).hasCount(0);
        assertThat(inputUsername).hasCount(0);
        assertThat(inputPassword).hasCount(0);
        assertThat(buttonLogin).hasCount(0);
    }
}
