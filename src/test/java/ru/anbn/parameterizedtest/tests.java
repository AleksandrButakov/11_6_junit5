package ru.anbn.parameterizedtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class tests {
// Открыть сайт https://ip-calculator.ru
// Ввод IP адреса и маски, расчет параметров подсети, проверка корректности выходных данных

// Тестовые данные (проверим только часть параметров)
// -- Проверить для запроса {"10.78.253.10", "29 - 255.255.255.248"}
// -- Ожидаемый результат {"29", "0.0.0.7", "10.78.253.8"}

    @BeforeEach
    void precondition() {
        open("https://ip-calculator.ru/#!ip=10.78.253.10/29");
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    // параметризованный тест
    @CsvSource(value = {
            // первые два параметра - входные данные, последние три - выходные. 29 и 28 прописали два раза
            // для удобства понимания данных
            "10.78.253.10|29|29|0.0.0.7|10.78.253.8",
            "10.78.250.55|28|28|0.0.0.15|10.78.250.48"
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка обображения результатов рассчета подсети IP фдреса \"{0}\"")
    void complexSearchTest(String ipAddressData, String networkData, String bitmaskResult, String wildcardResult,
                           String networkResult) {
        // заполним поле IP адрес
        $("#ip").setValue(ipAddressData);
        // заполним маску
        $("#mask").selectOptionByValue(networkData);
        // нажимаем кнопку "Посчитать"
        // <button type="submit" class="btn btn-primary btn-lg form-control input-lg">Подсчитать →</button>
        $("button[type='submit']").click();

        // проверка выходных данных
        // (bitmaskResult)
        $("#result > div > table > tbody > tr:nth-child(2) > td:nth-child(2)").shouldHave(text(bitmaskResult));
        // wildcardResult
        $x("//*[@id=\"result\"]/div/table/tbody/tr[4]/td[2]").shouldHave(text(wildcardResult));
        // networkResult
        $("#result > div > table > tbody > tr:nth-child(5) > td:nth-child(2)").shouldHave(text(networkResult));

    }


}
