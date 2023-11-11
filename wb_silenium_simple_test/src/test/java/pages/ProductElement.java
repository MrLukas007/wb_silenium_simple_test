package pages;

import com.codeborne.selenide.SelenideElement;

public class ProductElement {

    private SelenideElement product;

    private final String priceLocator = ".//*[@class = 'product-card__price price']//*[@class='price__lower-price']";
    private final String fakePriceLocator = ".//*[@class = 'product-card__price price']//del";
    private final String brandLocator = ".//*[@class = 'product-card__brand']";
    private final String productNameLocator = ".//*[@class = 'product-card__name']";

    public ProductElement(SelenideElement product) {
        this.product = product;
    }

    public String getPrice() {
        return product.$x(priceLocator).getText();
    }

    public String getFakePrice() {
        return product.$x(fakePriceLocator).getText();
    }

    public String getBrand() {
        return product.$x(brandLocator).getText();
    }

    public String getProductName() {
        return product.$x(productNameLocator).getOwnText();
    }

    public void openProductCard() {
        product.click();
    }
}
