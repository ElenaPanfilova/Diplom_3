package stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.api.UserApi;
import stellarburgers.model.User;
import stellarburgers.model.UserGenerator;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Регистрация")
class RegistrationTest extends BaseTest {

    private final UserApi userApi = new UserApi();
    private User user;
    private RegisterPage registerPage;

    @BeforeEach
    void setUp() {
        user = UserGenerator.randomUser();
        registerPage = new RegisterPage(driver);
        registerPage.open();
    }

    @AfterEach
    void tearDown() {
        if (user == null) {
            return;
        }
        String accessToken = userApi.login(user);
        if (accessToken != null) {
            userApi.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация открывает страницу входа")
    void successfulRegistrationOpensLoginPage() {
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(new LoginPage(driver).isLoginTitleDisplayed());
    }

    @Test
    @DisplayName("Пароль короче шести символов вызывает ошибку")
    void registrationWithShortPasswordShowsError() {
        registerPage.register(user.getName(), user.getEmail(), "12345");

        assertTrue(registerPage.isIncorrectPasswordErrorDisplayed());
    }
}
