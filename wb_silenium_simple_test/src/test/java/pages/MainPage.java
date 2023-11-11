package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public MainPage() {
        waitForLoader();
    }

    private final SelenideElement inputField = $x("//*[@id='searchInput']");
    private final SelenideElement basket = $x("//*[@data-wba-header-name='Cart']");
    private final String productsLocator = "//article";

    public void search(String value) {
        inputField.setValue(value);
        waitForLoader();
        inputField.pressEnter();
        waitForLoader();
    }

    public void waitForLoader() {
        $(".general-preloader").shouldNotBe(visible);
    }

    public SelenideElement getProduct() {
        return $x(productsLocator);
    }

    public BasketPage openBasket() {
        basket.click();
        waitForLoader();
        return new BasketPage().open();
    }
}
