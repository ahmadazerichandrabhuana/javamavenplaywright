import org.testng.annotations.Test;
class Test001 extends TestParent {

    @Test(description = "As a User, I will see Header \"Swag Labs\", input field Username, input field Password, and Login button on the Login Page")
    public void test() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.verifyLoginPageOK();
    }
}