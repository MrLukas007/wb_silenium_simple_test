package wb_tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.ProductDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ScreenShooterExtension.class)
@ExtendWith(TextReportExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WBTest {

    static MainPage mainPage;

    private ProductDto expectedProduct;

    @BeforeAll
    void beforeAll() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "";
        Configuration.browserCapabilities = chromeOptions;
        Configuration.browserSize = null;
        Configuration.timeout = 30_000;
    }

    @Order(1)
    @Test
    @DisplayName("Открываем сайт Wildberries")
    void openWB() {
        String baseUrl = "https://www.wildberries.ru/";
        open(baseUrl);

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();

        assertEquals(baseUrl, currentUrl, "Incorrect base url");
    }

    @Order(2)
    @Test
    @DisplayName("Вызываем поиск")
    void search() {
        mainPage = new MainPage();

        String searchValue = "Видеокарта nVidia GeForce RTX 4090 Gaming X Trio 24G";

        mainPage.search(searchValue);

        assertThat(mainPage.getProduct().getText().toLowerCase()).as("Incorrect article text").contains(searchValue.toLowerCase());
    }

    @Order(3)
    @Test
    @DisplayName("Добавляем покупку в корзину")
    void addedToBasket() {
        ProductElement productElement = new ProductElement(mainPage.getProduct());

        expectedProduct = new ProductDto(
                productElement.getProductName(),
                productElement.getBrand(),
                productElement.getPrice(),
                productElement.getFakePrice()
        );

        productElement.openProductCard();

        mainPage.waitForLoader();

        ProductCardElement productCardElement = new ProductCardElement();

        checkProductInfoOnDetailPage(productCardElement, expectedProduct);

        productCardElement.addToBasket();

        back();

        mainPage.waitForLoader();
    }

    @Order(4)
    @Test
    @DisplayName("Открываем корзину и проверяем покупки")
    void checkBasketProducts() {
        BasketPage basketPage = mainPage.openBasket();

        BasketProductElement basketProductElement = new BasketProductElement(basketPage.getBasketProduct());

        basketProductElement.waitForPriceLoaded(expectedProduct.getPrice());

        checkProductInfoOnBasketPage(basketProductElement, expectedProduct);
    }

    private void checkProductInfoOnDetailPage(ProductCardElement productCard, ProductDto expectedProduct) {
        assertThat(productCard.getProductName()).isEqualTo(expectedProduct.getName());
        assertThat(productCard.getBrand()).isEqualTo(expectedProduct.getBrand());
        assertThat(productCard.getPrice()).isEqualTo(expectedProduct.getPrice());
        assertThat(productCard.getFakePrice()).isEqualTo(expectedProduct.getFakePrice());
    }

    private void checkProductInfoOnBasketPage(BasketProductElement basketProductElement, ProductDto expectedProduct) {
        assertThat(basketProductElement.getProductName()).isEqualTo(expectedProduct.getName());
        assertThat(basketProductElement.getBrand()).isEqualTo(expectedProduct.getBrand());
        assertThat(basketProductElement.getPrice()).isEqualTo(expectedProduct.getPrice());
        assertThat(basketProductElement.getFakePrice()).isEqualTo(expectedProduct.getFakePrice());
    }
}
