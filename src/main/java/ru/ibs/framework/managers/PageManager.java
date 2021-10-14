package ru.ibs.framework.managers;

import ru.ibs.framework.pages.CartPage;
import ru.ibs.framework.pages.ListProductPage;
import ru.ibs.framework.pages.StartPage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private static StartPage startPage;

    /**
     * Страничка листа товаров
     */
    private static ListProductPage listProductPage;

    /**
     * Страничка корзины
     */
    private static CartPage cartPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Метод ленивой инициализации PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Метод ленивой инициализации {@link StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Метод ленивой инициализации {@link ListProductPage}
     *
     * @return ListProductPage
     */
    public ListProductPage getListProductPage() {
        if (listProductPage == null) {
            listProductPage = new ListProductPage();
        }
        return listProductPage;
    }

    /**
     * Метод ленивой инициализации {@link CartPage}
     *
     * @return CartPage
     */
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
}