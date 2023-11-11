package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class BasketProductElement {

    private SelenideElement basketProduct;

    private final String productNameLocator = ".//*[@class='good-info__good-name']";
    private final String brandLocator = ".//*[@class = 'good-info__good-brand ']";
    private final String priceLocator = ".//*[@class = 'list-item__price-new']";
    private final String fakePriceLocator = ".//*[@class = 'list-item__price-old']";

    public BasketProductElement(SelenideElement basketProduct) {
        this.basketProduct = basketProduct;
    }

    public String getPrice() {
        return basketProduct.$x(priceLocator).getText();
    }

    public String getFakePrice() {
        return basketProduct.$x(fakePriceLocator).getText();
    }

    public String getBrand() {
        String brand = basketProduct.$x(brandLocator).getText();
        return brand.substring(2);
    }

    public String getProductName() {
        return basketProduct.$x(productNameLocator).getOwnText();
    }

    public void waitForPriceLoaded(String price) {
        basketProduct.$x(priceLocator).shouldBe(Condition.text(price));
    }

}
