package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.product.Product;


import java.util.List;
import java.util.concurrent.TimeUnit;



public class ListProductPage extends BasePage {

    @FindBy(xpath = "//a[@class = '_1-6r']")
    private WebElement next;

    @FindBy(xpath = "//div[@class = 'bi3 bi5']")
    private List<WebElement> productList;

    @FindBy(xpath = "//input[@class = '_16XE _2HHF']")
    private WebElement addressField;

    @FindBy(xpath = "//div[@class = 'g5g9']//button[@type = 'button']")
    private WebElement addressBtn;

    @FindBy(xpath = "//li[@class = 'g5h4']")
    private List<WebElement> addressList;

    @FindBy(xpath = "//div[contains(text(), 'Цена')]/..//p[contains(text(), 'от')]/../input[@type = 'text']]")
    private WebElement priceFrom;

    @FindBy(xpath = "//div[contains(text(), 'Цена')]/..//p[contains(text(), 'до')]/../input[@type = 'text']")
    private WebElement priceTo;

    @FindBy(xpath = "//label[@class = '_3_Yb _2o8b _35W4 _3e7l g1l1']")
    private List<WebElement> checkboxList;

    @FindBy(xpath = "//div[contains(text(), 'Беспроводные интерфейсы')]/..//label")
    private List<WebElement> interfaceList;

    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/..//span[contains(text(), 'Посмотреть все')]")
    private WebElement moreBtn;

    @FindBy(xpath = "//div[contains(text(), 'Бренды')]/..//input[@type = 'text']")
    private WebElement moreField;

    @FindBy(xpath = "//div[@class = 'g1n3']//label")
    private List<WebElement> brandList;

    @FindBy(xpath = "//div[@class = 'B1so Dr7V']//span")
    private List<WebElement> filterList;

    @FindBy(xpath = "//a[@href = '/cart']")
    private WebElement cart;

    @FindBy(xpath = "//div[@class = 'g5g9']")
    private WebElement address;

    String filterPath = "//div[@class = 'B1so Dr7V']//span[contains(text(), '%s')]";

    /**
     * Метод заполнения фильтра цены
     *
     * @param name  - название поля
     * @param value - значение
     * @return ListProductPage - т.е. остаемся на этой странице
     */
    public ListProductPage filterPriceFromTo(String name, String value) {
        switch (name) {
            case "от":
                fillInputField(priceFrom, value);
                waitUtilElementToBeVisible(driverManager.getWebDriver().findElement(By.xpath(String.format(filterPath, name, " ", value))));
                return this;
            case "до":
                fillInputField(priceTo, value);
                waitUtilElementToBeVisible(driverManager.getWebDriver().findElement(By.xpath(String.format(filterPath, name, " ", value))));
                return this;
            default:
                Assertions.fail("Поля " + name + " нет в фильтре 'цена'");
                return this;
        }
    }

    /**
     * Метод изменения состояния checkbox
     *
     * @param name - название checkbox
     * @return ListProductPage - т.е. остаемся на этой странице
     */
    public ListProductPage checkboxClick(String name) {
        for (WebElement checkbox : checkboxList) {
            WebElement checkboxText = checkbox.findElement(By.xpath(".//span[@class = 'g1l4']"));
            if (checkboxText.getText().contains(name)) {
                WebElement checkboxBtn = checkbox.findElement(By.xpath(".//div[@class = '_3eqP']"));
                action.moveToElement(checkboxBtn).click().build().perform();
                waitUtilElementToBeVisible(driverManager.getWebDriver().findElement(By.xpath(String.format(filterPath, name))));
                return this;
            }
        }
        Assertions.fail("Checkbox " + name + " нет на странице");
        return this;
    }

    /**
     * Метод выбора фильтра по бренду
     *
     * @param brandName - название бренда
     * @return ListProductPage - т.е. остаемся на этой странице
     */
    public ListProductPage selectBrand(String brandName) {
        driverManager.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            moreBtn.click();
        } catch (NoSuchElementException ignore) {

        } finally {
            driverManager.getWebDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        fillInputField(moreField, brandName);
        for (WebElement brand : brandList) {
            WebElement brandText = brand.findElement(By.xpath(".//span[@class = 'g1p7']"));
            if (brandName.equalsIgnoreCase(brandText.getText())) {
                WebElement brandBtn = brand.findElement(By.xpath(".//div[@class = '_3eqP']"));
                brandBtn.click();
                waitUtilElementToBeVisible(driverManager.getWebDriver().findElement(By.xpath(String.format(filterPath, brandName))));
                return this;
            }
        }
        return this;
    }

    /**
     * Метод добавления фильтра в блоке 'Беспроводные интерфейсы'
     *
     * @param name - название фильтра
     * @return ListProductPage - т.е. остаемся на этой странице
     */
    public ListProductPage selectWirelessInterfaces(String name) {
        for (WebElement block : interfaceList) {
            WebElement text = block.findElement(By.xpath(".//span[@class = 'g1p7']"));
            if (name.equalsIgnoreCase(text.getText())) {
                WebElement btn = block.findElement(By.xpath(".//div[@class = '_3eqP']"));
                action.moveToElement(btn).click().build().perform();
                waitUtilElementToBeVisible(driverManager.getWebDriver().findElement(By.xpath(String.format(filterPath, name))));
                return this;
            }
        }
        Assertions.fail("Фильтра " + name + " нет в блоке 'Беспроводные интерфейсы'");
        return this;
    }

    /**
     * Метод добавления 8 четных товаров в корзину
     *
     * @return ListProductPage - т.е. остаемся на этой странице
     */
    public ListProductPage addProduct() {
        int count = 0;
        while (count != 8) {
            for (int i = 1; i < productList.size(); i = i + 2) {
                List<WebElement> addBtn = productList.get(i).findElements(By.xpath(".//div[contains(@class, 'b0d1')]//button"));
                List<WebElement> btnStatus = productList.get(i).findElements(By.xpath(".//div[contains(@class, 'b0d1')]//div[contains(@class, 'b0d') and not(contains(@class, 'a'))]"));
                for (int j = 0; j < btnStatus.size(); j++) {
                    if (btnStatus.get(j).getAttribute("className").contains("b0d5") && !(addBtn.get(0).getAttribute("innerText").equalsIgnoreCase("Похожие"))) {
                        WebElement productName = productList.get(i).findElement(By.xpath(".//span[@class = 'a7y a8a2 a8a6 a8b2 f-tsBodyL bj5']//span"));
                        WebElement productPrise = productList.get(i).findElement(By.xpath(".//span[contains(@class, '_2DV4 _17o0')]"));
                        listProductManager.getProductList().add(new Product(parsePrice(productPrise.getText()), productName.getText()));
                        String value = cart.getAttribute("text");
                        action.moveToElement(addBtn.get(j)).click().build().perform();
                        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(cart, "text", value)));
                        count++;
                    }
                    if (count == 8) {

                        break;
                    }
                }
            }
            waitUtilElementToBeClickable(next).click();
        }
        return this;
    }

    /**
     * Метод добавления всех четных товаров в корзину
     *
     * @return ListProductPage - т.е. остаемся на этой странице
     */
    public ListProductPage addAllProducts() {
        while (true) {
            for (int i = 1; i < productList.size(); i = i + 2) {
                List<WebElement> addBtn = productList.get(i).findElements(By.xpath(".//div[contains(@class, 'b0d1')]//button"));
                List<WebElement> btnStatus = productList.get(i).findElements(By.xpath(".//div[contains(@class, 'b0d1')]//div[contains(@class, 'b0d') and not(contains(@class, 'a'))]"));
                for (int j = 0; j < btnStatus.size(); j++) {

                    if (btnStatus.get(j).getAttribute("className").contains("b0d5") && !(addBtn.get(0).getAttribute("innerText").equalsIgnoreCase("Похожие"))) {

                        WebElement productName = productList.get(i).findElement(By.xpath(".//span[@class = 'a7y a8a2 a8a6 a8b2 f-tsBodyL bj5']//span"));
                        WebElement productPrise = productList.get(i).findElement(By.xpath(".//span[contains(@class, '_2DV4 _17o0')]"));
                        listProductManager.getProductList().add(new Product(parsePrice(productPrise.getText()), productName.getText()));

                        String value = cart.getAttribute("text");
                        action.moveToElement(addBtn.get(j)).click().build().perform();
                        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(cart, "text", value)));
                    }

                }
            }

            driverManager.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            try {
                waitUtilElementToBeClickable(next).click();
            } catch (NoSuchElementException | TimeoutException e) {
                break;
            } finally {
                driverManager.getWebDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            }


        }
        return this;
    }
}
