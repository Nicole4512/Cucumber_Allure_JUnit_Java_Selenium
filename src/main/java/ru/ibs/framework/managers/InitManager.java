package ru.ibs.framework.managers;


import java.util.concurrent.TimeUnit;
import static ru.ibs.framework.utils.PropConst.IMPLICITLY_WAIT;
import static ru.ibs.framework.utils.PropConst.PAGE_LOAD_TIMEOUT;
import static ru.ibs.framework.utils.PropConst.BASE_URL;

/**
 * Класс для инициализаци и завершения работы фреймворка
 */
public class InitManager {

    /**
     * Объект TestPropManager
     *
     * @see TestPropManager#getTestPropManager()
     */
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Объект DriverManager
     *
     * @see DriverManager#getDriverManager()
     */
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Метод инициализации фреймворка
     *
     * @see TestPropManager#getProperty(String)
     * @see ru.ibs.framework.utils.PropConst
     * @see DriverManager#getWebDriver()
     */
    public static void initFramework(){
        driverManager.getWebDriver().manage().window().maximize();
        driverManager.getWebDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getWebDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getWebDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    /**
     * Метод завершения работы фреймворка (закрытие сессии с браузером, driver = null)
     *
     * @see DriverManager#quitDriver()
     */
    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
