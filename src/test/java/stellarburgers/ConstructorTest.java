package stellarburgers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stellarburgers.pageobject.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Раздел «Конструктор»")
class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    void bunsTabIsSelectedAfterClick() {
        mainPage.clickSaucesTab();
        mainPage.waitForSaucesTabSelected();
        mainPage.clickBunsTab();

        assertTrue(mainPage.isBunsTabSelected());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    void saucesTabIsSelectedAfterClick() {
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesTabSelected());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    void fillingsTabIsSelectedAfterClick() {
        mainPage.clickFillingsTab();

        assertTrue(mainPage.isFillingsTabSelected());
    }
}
