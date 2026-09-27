package stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import stellarburgers.driver.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void startBrowser() {
        driver = DriverFactory.createDriver();
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
