package ru.anbn;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ParameterizedSearchTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru");
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    // Параметризованный тест
    @Disabled
    @ValueSource(strings = {"Selenide", "JUnit 5"})
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в яндексе для запроса \"{0}\"")
    void commonSearchTest(String testData) {
        // ввести в поле "Selenide"
        Selenide.$("#text").setValue(testData);
        // нажать кнопку "Найти"
        /* <button class="button mini-suggest__button button_theme_search button_size_search-large
         * i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button"
         * type="submit"><span class="button__text">Найти</span></button>
         */
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text(testData)).shouldBe(visible);
    }

    /* в аннотации @CsvSource запятая с пробелом разделяет аргументы
     * в testData будет передано "Selenide", в expectedText будет передано ": Concise UI Tests with Java!"
     * если в данных надо передать запятую, добавляем delimiter = '/' и после этого разделителем
     * становится "/ "
     */
    @CsvSource(value = {
            "Selenide/ : Concise UI Tests with Java!",
            "JUnit 5/  | IntelliJ IDEA"
    },
            delimiter = '/'
    )
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в яндексе для запроса \"{0}\"")
    void complexSearchTest(String testData, String expectedText) {
        // ввести в поле "Selenide"
        Selenide.$("#text").setValue(testData);
        // нажать кнопку "Найти"
        /* <button class="button mini-suggest__button button_theme_search button_size_search-large
         * i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button"
         * type="submit"><span class="button__text">Найти</span></button>
         */
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text(expectedText)).shouldBe(visible);
    }

}
