package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SmsPageTest extends TestBase {

    @Test
    void checkErrorMassege() {
        step("Открываем главную страницу", () ->
                open("")
        );

        step("Есть подпска выбираем", () -> {
            $(byText("Да")).click();
            $(byText("Продолжить")).click();
        });

        step("Выбирем спорт", () -> {
            $(withText("спорта")).click();
            $(byText("Продолжить")).click();
        });

        step("Ввод номер телефона", () -> {
            $("input").setValue("9991231234").pressEnter();
            $(byText("Получить код")).click();
        });

        step("Ввод смс ошибочный кода", () -> {
            $("input", 0).setValue("10110");
        });

        step("Ввод смс ошибочный кода", () -> {
            $(byText("Неверный код")).shouldBe(Condition.visible);
        });
    }
}
