package ru.ibs.framework.managers;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static ru.ibs.framework.utils.PropConst.*;

/**
 * Класс для управления веб драйвером
 */
public class DriverManager {

    /**
     * Переменная для хранения драйвера
     *
     * @see WebDriver
     */
    private WebDriver driver;

    /**
     * Переменная для хранения объекта DriverManager
     */
    private static DriverManager driverManager = null;

    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * privat конструктор DriverManager (singleton паттерн)
     */
    private DriverManager() {
    }

    /**
     * Метод, раелизующий "ленивую" инициализацию объекта DriverManager
     *
     * @return DriverManager
     */
    public static DriverManager getDriverManager() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    /**
     * Метод, раелизующий "ленивую" инициализацию веб драйвера
     *
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        if (driver == null) {
            initDriver(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
        }
        return driver;
    }

    /**
     * Метод для закрытия сессии и браузера
     *
     * @see WebDriver#quit()
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    /**
     * Метод для инициализации драйвера
     *
     * @param gecko  - переменная firefox из файла application.properties в классе {@link ru.ibs.framework.utils.PropConst}
     * @param chrome - переменная chrome из файла application.properties в классе {@link ru.ibs.framework.utils.PropConst}
     */

    private void initDriver(String gecko, String chrome) {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("Тип браузера " + props.getProperty(TYPE_BROWSER) + " не существует в фреймворке");

        }
    }
}