package stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private static final String URL = BASE_URL + "/";

    private final By constructorTitle = By.xpath("//h1[text()='Соберите бургер']");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By bunsTab = By.xpath("//div[contains(@class,'tab_tab')][span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[contains(@class,'tab_tab')][span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[contains(@class,'tab_tab')][span[text()='Начинки']]");
    private final By selectedBunsTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')][span[text()='Булки']]");
    private final By selectedSaucesTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')][span[text()='Соусы']]");
    private final By selectedFillingsTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')][span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(URL);
    }

    @Step("Нажать «Войти в аккаунт»")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Перейти в раздел «Булки»")
    public void clickBunsTab() {
        click(bunsTab);
    }

    @Step("Перейти в раздел «Соусы»")
    public void clickSaucesTab() {
        click(saucesTab);
    }

    @Step("Перейти в раздел «Начинки»")
    public void clickFillingsTab() {
        click(fillingsTab);
    }

    @Step("Дождаться входа в аккаунт")
    public void waitForLogin() {
        waitForVisibility(orderButton);
    }

    @Step("Дождаться, что выбран раздел «Соусы»")
    public void waitForSaucesTabSelected() {
        waitForVisibility(selectedSaucesTab);
    }

    @Step("Проверить, что открыт конструктор")
    public boolean isConstructorDisplayed() {
        return isDisplayed(constructorTitle);
    }

    @Step("Проверить, что видна кнопка «Оформить заказ»")
    public boolean isOrderButtonDisplayed() {
        return isDisplayed(orderButton);
    }

    @Step("Проверить, что выбран раздел «Булки»")
    public boolean isBunsTabSelected() {
        return isDisplayed(selectedBunsTab);
    }

    @Step("Проверить, что выбран раздел «Соусы»")
    public boolean isSaucesTabSelected() {
        return isDisplayed(selectedSaucesTab);
    }

    @Step("Проверить, что выбран раздел «Начинки»")
    public boolean isFillingsTabSelected() {
        return isDisplayed(selectedFillingsTab);
    }
}
