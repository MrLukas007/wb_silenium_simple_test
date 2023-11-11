package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    private final SelenideElement purchase = $x("//*[@class='basket-order__b-btn b-btn']");

    private final String basketProductsLocator = "//*[@class='list-item__wrap']";

    public BasketPage open() {
        purchase.shouldBe(Condition.visible);
        return this;
    }

    public SelenideElement getBasketProduct() {
        return $x(basketProductsLocator);
    }
}
