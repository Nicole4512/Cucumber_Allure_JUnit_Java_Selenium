package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;


public class CartPageStaps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Закрываем окно рекламы$")
    public void closeAd(){
        pageManager.getCartPage().closeAd();
    }

    @И("^Проверяем, что все добавленные ранее товары находятся в корзине$")
    public void checkProductList(){
        pageManager.getCartPage().checkProductList();

    }

    @И("Прикрепляем файл")
    public void attachFile(){
        pageManager.getCartPage().attachFile();
    }


    @И("^Удаляем все товары из корзины$")
    public void delAll(){
        pageManager.getCartPage().delAll();
    }

    @И("^Проверяем, что корзина пуста$")
    public void checkDel(){
        pageManager.getCartPage().checkDel();
    }

    @И("^Проверяем, что текст корзины «Ваша корзина  - (.+)»$")
    public void checkText(String text){
        pageManager.getCartPage().checkText(text);
    }


}
