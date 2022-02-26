package ru.anbn;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

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
        System.out.println("Test_01");
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
        System.out.println("Test_02");
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


    /* название статического метода mixesArgumentsTest() соответствует имени метода в @MethodSource
     * если бы не соответствовало, пришлось отдельно бы указывать это, что мы и сделали ниже
     * ниже написали метод в котором указали все необходимые данные, потом эти данные передаем в @MethodSource
     */
    static Stream<Arguments> mixesArgumentsTestDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", List.of(1, 2, 3), true),
                Arguments.of("Junit 5", List.of(5, 6, 7), false)
        );
    }

    @MethodSource(value = "mixesArgumentsTestDataProvider")
    @ParameterizedTest
    /* @ParameterizedTest(name = "name {2}") - если так укажем то присвоим переменной name данные с индексом 2
     * т.е. name = true, потом false
     */

    void mixedArgumentsTest(String firstArg, List<Integer> secondArg, boolean aBooleanValue) {
        System.out.println("String: " + firstArg + "List: " + secondArg.toString() + " boolean: " + aBooleanValue);
    }


    

}
