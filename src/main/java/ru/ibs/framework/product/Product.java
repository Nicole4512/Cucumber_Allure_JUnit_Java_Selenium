package ru.ibs.framework.product;

public class Product {
    int price;
    String name;

    public Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Название: " + name + " Цена: " + price + "\n";
    }
}
