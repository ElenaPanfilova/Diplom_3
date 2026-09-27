package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final String URL = BASE_URL + "/register";

    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By incorrectPasswordError = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(URL);
    }

    @Step("Заполнить форму регистрации и отправить")
    public void register(String name, String email, String password) {
        type(nameInput, name);
        type(emailInput, email);
        type(passwordInput, password);
        click(registerButton);
    }

    @Step("Нажать «Войти» в форме регистрации")
    public void clickLoginLink() {
        click(loginLink);
    }

    @Step("Проверить, что показана ошибка «Некорректный пароль»")
    public boolean isIncorrectPasswordErrorDisplayed() {
        return isDisplayed(incorrectPasswordError);
    }
}
