package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {

    private final By constructorLink = By.xpath("//p[text()='Конструктор']");
    private final By accountLink = By.xpath("//p[text()='Личный Кабинет']");
    private final By logoLink = By.xpath("//div[contains(@class,'AppHeader_header__logo')]/a");

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать «Конструктор» в шапке")
    public void clickConstructorLink() {
        click(constructorLink);
    }

    @Step("Нажать «Личный кабинет» в шапке")
    public void clickAccountLink() {
        click(accountLink);
    }

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        click(logoLink);
    }
}
