package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private static final String URL = BASE_URL + "/forgot-password";

    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get(URL);
    }

    @Step("Нажать «Войти» в форме восстановления пароля")
    public void clickLoginLink() {
        click(loginLink);
    }
}
