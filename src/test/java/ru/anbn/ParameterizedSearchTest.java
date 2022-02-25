package ru.anbn;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Проверка отображения поисковых результатов в яндексе для запроса \"Selenide\"")
    void selenideSearchTest() {
        // ввести в поле "Selenide"
        Selenide.$("#text").setValue("Selenide");
        // нажать кнопку "Найти"
        /* <button class="button mini-suggest__button button_theme_search button_size_search-large
         * i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button"
         * type="submit"><span class="button__text">Найти</span></button>
         */
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text("Selenide")).shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка отображения поисковых результатов в яндексе для запроса \"JUnit 5\"")
    void junitSearchTest() {
        // ввести в поле "Selenide"
        Selenide.$("#text").setValue("JUnit 5");
        // нажать кнопку "Найти"
        /* <button class="button mini-suggest__button button_theme_search button_size_search-large
         * i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button"
         * type="submit"><span class="button__text">Найти</span></button>
         */
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text("JUnit 5")).shouldBe(visible);
    }

}
