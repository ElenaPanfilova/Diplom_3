package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    private final By profileLink = By.xpath("//a[text()='Профиль']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Дождаться загрузки личного кабинета")
    public void waitForLoad() {
        waitForVisibility(profileLink);
    }

    @Step("Нажать «Выход»")
    public void clickLogoutButton() {
        click(logoutButton);
    }

    @Step("Проверить, что открыт личный кабинет")
    public boolean isProfileDisplayed() {
        return isDisplayed(profileLink);
    }
}
