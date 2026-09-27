package stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.api.UserApi;
import stellarburgers.model.User;
import stellarburgers.model.UserGenerator;
import stellarburgers.pageobject.Header;
import stellarburgers.pageobject.LoginPage;
import stellarburgers.pageobject.MainPage;
import stellarburgers.pageobject.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Личный кабинет")
class AccountTest extends BaseTest {

    private final UserApi userApi = new UserApi();
    private String accessToken;
    private Header header;
    private MainPage mainPage;
    private ProfilePage profilePage;

    @BeforeEach
    void setUp() {
        User user = UserGenerator.randomUser();
        accessToken = userApi.create(user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(user.getEmail(), user.getPassword());

        header = new Header(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        mainPage.waitForLogin();
    }

    @AfterEach
    void tearDown() {
        if (accessToken != null) {
            userApi.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    void accountLinkOpensProfile() {
        header.clickAccountLink();

        assertTrue(profilePage.isProfileDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    void constructorLinkOpensConstructorFromProfile() {
        header.clickAccountLink();
        profilePage.waitForLoad();
        header.clickConstructorLink();

        assertTrue(mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип")
    void logoOpensConstructorFromProfile() {
        header.clickAccountLink();
        profilePage.waitForLoad();
        header.clickLogo();

        assertTrue(mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выход» в личном кабинете")
    void logoutButtonOpensLoginPage() {
        header.clickAccountLink();
        profilePage.clickLogoutButton();

        assertTrue(new LoginPage(driver).isLoginTitleDisplayed());
    }
}
