package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class ProductCardElement {

    private final SelenideElement basket = $x("//*[@class = 'product-page__aside']//*[@class = 'order']");

    private final SelenideElement brand = $x("//*[@class= 'product-page__header']//span");
    private final SelenideElement productName = $x("//*[@class= 'product-page__header']//h1");
    private final SelenideElement price = $x("//*[@class= 'product-page__aside-sticky']//*[@class= 'price-block__price']");
    private final SelenideElement fakePrice = $x("//*[@class= 'product-page__aside-sticky']//del");

    public String getBrand() {
        return brand.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public String getFakePrice() {
        return fakePrice.getText();
    }

    public void addToBasket() {
        basket.click();
        waitForBasketNotification();
    }

    private void waitForBasketNotification() {
        $x("//*[@class='action-notification__text']").shouldBe(exist);
    }
}
