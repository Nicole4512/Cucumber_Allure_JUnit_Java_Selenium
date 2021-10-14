package ru.ibs.framework.pages;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.product.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(xpath = "//div[@class = 'g5c8 _2avF']//button[@class= '_1-6r']")
    private WebElement closeBtn;

    @FindBy(xpath = "//a[@class = 'a7n3']//span[contains(@style, 'color:')]")
    private List<WebElement> productNameList;

    @FindBy(xpath = "//span[@class = 'b4i9 b4j']")
    private WebElement delBtn;

    @FindBy(xpath = "//div[@class = '_2DsC']//div[@class = 'q7 _2avF']//button")
    private WebElement confirmDel;

    @FindBy(xpath = "//div[@class = 'a5a3']")
    private WebElement text;

    @FindBy(xpath = "//div[@data-widget = 'emptyCart']")
    private WebElement emptyCart;

    public CartPage closeAd() {
        waitUtilElementToBeClickable(closeBtn).click();
        return this;
    }

    /**
     * Метод роверки, что все добавленные ранее товары находятся в корзине
     *
     * @return CartPage - т.е. остаемся на этой странице
     */
    public CartPage checkProductList() {
        List<String> nameList = new ArrayList<>();
        for (WebElement product : productNameList) {
            nameList.add(product.getText());
        }
        if (nameList.size() == listProductManager.getProductList().size()) {
            for (Product myProduct : listProductManager.getProductList()) {
                if (!(nameList.contains(myProduct.getName()))) {
                    Assertions.fail("Продукта " + myProduct.getName() + " нет в корзине");
                }
            }
        } else {
            Assertions.fail("Количество продуктов в корзине не соответствует ожидаемому" +
                    listProductManager.getProductList().size() + "  " + nameList.size());
        }
        return this;
    }

    /**
     * Метод удаления всех товаров из корзины
     *
     * @return CartPage - т.е. остаемся на этой странице
     */
    public CartPage delAll() {
        waitUtilElementToBeClickable(delBtn).click();
        waitUtilElementToBeClickable(confirmDel).click();
        return this;
    }


    /**
     * Метод проверки текста корзины
     *
     * @return CartPage - т.е. остаемся на этой странице
     */
    public CartPage checkText(String cartText) {
        if (!(text.getAttribute("innerText").contains("Ваша корзина") && text.getAttribute("innerText").contains(cartText))) {
            Assertions.fail("Текст не соответствует ожидаемому");
        }
        return this;
    }

    /**
     * Метод проверки того, что корзина пуста
     *
     * @return CartPage - т.е. остаемся на этой странице
     */
    public CartPage checkDel() {
        if (!(emptyCart.isDisplayed())) {
            Assertions.fail("Корзина не пуста");
        }
        return this;
    }

    /**
     * Метод добавления файла в allure отчет
     *
     * @return CartPage - т.е. остаемся на этой странице
     */
    public CartPage attachFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("output.txt");
            for (Product product : listProductManager.getProductList()) {
                String name = product.getName();
                int price = product.getPrice();
                writer.write(name + " " + price + System.getProperty("line.separator"));
            }
            writer.write("Продукт(ы) с наибольшей ценой: \n");
            for (Product maxProduct : listProductManager.getMaxList()) {
                String name = maxProduct.getName();
                int price = maxProduct.getPrice();
                writer.write(name + " " + price + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Path content = Paths.get("output.txt");
            try (InputStream file = Files.newInputStream(content)) {
                Allure.addAttachment("Product", file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

}
