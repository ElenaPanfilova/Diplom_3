package stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.api.UserApi;
import stellarburgers.model.User;
import stellarburgers.model.UserGenerator;
import stellarburgers.pageobject.ForgotPasswordPage;
import stellarburgers.pageobject.Header;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.MainPage;
import stellarburgers.pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Вход")
class LoginTest extends BaseTest {

    private final UserApi userApi = new UserApi();
    private User user;
    private String accessToken;
    private MainPage mainPage;
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        user = UserGenerator.randomUser();
        accessToken = userApi.create(user);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (accessToken != null) {
            userApi.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    void loginByLoginButtonOnMainPage() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    void loginByAccountLink() {
        mainPage.open();
        new Header(driver).clickAccountLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    void loginByLinkOnRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    void loginByLinkOnForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonDisplayed());
    }
}
