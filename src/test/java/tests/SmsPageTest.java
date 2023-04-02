package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@ExtendWith({AllureJunit5.class})
public class SmsPageTest extends TestBase {

    @Test
    @Description("Check error on sms input")
    @DisplayName("Check error on sms input")
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
            $("input").setValue("9991231235");
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
