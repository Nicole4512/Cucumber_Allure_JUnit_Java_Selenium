package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;


public class ListProductPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Заполняем фильтр цены '(.+)' значением '(.+)'$")
    public void filterPriceFromTo(String name, String value){
        pageManager.getListProductPage().filterPriceFromTo(name, value);
    }

    @И("^Изменяем состояние checkbox '(.+)'$")
    public void checkboxClick(String name){
        pageManager.getListProductPage().checkboxClick(name);
    }

    @И("^Заполняем фильтр брендов:$")
    public void selectBrand(DataTable dataTable){
        for (String brandName : dataTable.asList()) {
            pageManager.getListProductPage().selectBrand(brandName);
        }
    }

    @И("^Отмечаем '(.+)' в фильтре 'Беспроводные интерфейсы'$")
    public void selectWirelessInterfaces(String name){
        pageManager.getListProductPage().selectWirelessInterfaces(name);

    }

    @И("^Добавляем 8 четных товаров$")
    public void addProduct(){
        pageManager.getListProductPage().addProduct();
    }

    @И("^Переходим в меню '(.+)'$")
    public void selectMenu(String menuName){
        pageManager.getListProductPage().getHeaderBlock().selectMenu(menuName);
    }

    @И("^Добавляем все четные товары$")
    public void  addAllProducts(){
        pageManager.getListProductPage().addAllProducts();
    }

}
