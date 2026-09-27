package stellarburgers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class DriverFactory {

    private static final String BROWSER = System.getProperty("browser", "chrome");
    private static final String YANDEX_DRIVER_PATH =
            System.getProperty("yandex.driver", "C:/WebDriver/yandexdriver.exe");
    private static final String YANDEX_BROWSER_PATH =
            System.getProperty("yandex.binary",
                    System.getenv("LOCALAPPDATA") + "/Yandex/YandexBrowser/Application/browser.exe");

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--no-first-run", "--no-default-browser-check");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));

        if ("yandex".equalsIgnoreCase(BROWSER)) {
            System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
            options.setBinary(YANDEX_BROWSER_PATH);
        }

        WebDriver driver = new ChromeDriver(options);
        openCleanTab(driver);
        return driver;
    }

    // Яндекс Браузер может подключить драйвер к служебной невидимой вкладке,
    // поэтому открываем новую обычную вкладку и закрываем все остальные
    private static void openCleanTab(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.TAB);
        String mainTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                driver.close();
            }
        }
        driver.switchTo().window(mainTab);
    }
}
