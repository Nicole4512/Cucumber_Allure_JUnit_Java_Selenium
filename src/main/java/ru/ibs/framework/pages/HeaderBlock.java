package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HeaderBlock extends BasePage {
    @FindBy(xpath = "//div[@class = 'g5j6']//a[@target = '_self']")
    private List<WebElement> menuList;

    @FindBy(xpath = "//input[@name = 'text']")
    private WebElement finder;

    @FindBy(xpath = "//button[@class = 'f9k']")
    private WebElement findBnt;

    /**
     * Метод ввода значения в поле поиска и клика на кнопку поиска
     *
     * @param value - значение для ввода
     * @return ListProductPage - т.е. переходим на страницу листа товаров
     */
    public ListProductPage fillFinder(String value) {
        fillInputField(finder, value);
        waitUtilElementToBeClickable(findBnt).click();
        return pageManager.getListProductPage();
    }

    /**
     * Метод выбора меню
     *
     * @param menuName - название меню
     * @return CartPage - переходим на страницу корзины
     */
    public CartPage selectMenu(String menuName) {
        for (WebElement menu : menuList) {
            WebElement menuText = menu.findElement(By.xpath(".//span[@class = 'e3i2']"));
            if (menuName.equalsIgnoreCase(menuText.getText())) {
                waitUtilElementToBeClickable(menu).click();
                return pageManager.getCartPage();
            }
        }
        Assertions.fail("" + menuName + "");
        return pageManager.getCartPage();
    }


}
