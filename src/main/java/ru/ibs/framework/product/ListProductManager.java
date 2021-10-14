package ru.ibs.framework.product;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для управления веб листом продуктов
 */
public class ListProductManager {

    private List<Product> productList = new ArrayList<>();

    private static ListProductManager INSTANCE = null;

    /**
     * privat конструктор ListProductManager (singleton паттерн)
     */
    private ListProductManager() {
    }

    /**
     * Метод ленивой инициализации ListProductManager
     *
     * @return ListProductManager
     */
    public static ListProductManager getListProductManager() {
        if (INSTANCE == null) {
            INSTANCE = new ListProductManager();
        }
        return INSTANCE;
    }

    /**
     * Геттер для productList
     * @return List<Product> - возвращает productList
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Сеттер для productList
     * @param productList - принимает лист продуктов
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * Метод поиска товара/ов с наибольшей ценой
     *
     * @return List<Product> - лист товаров с наибольшей ценой
     */
    public List<Product> getMaxList() {
        List<Product> myList = new ArrayList<>();
        int max = 0;
        for (Product product : productList) {
            if (product.getPrice() > max) {
                max = product.price;
            }
        }
        for (Product product : productList) {
            if (product.getPrice() == max) {
                myList.add(product);
            }
        }
        return myList;
    }


}
