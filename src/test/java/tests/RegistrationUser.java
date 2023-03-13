package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationUser extends TestBase {

    @Test
    @Description("Проверка поиска")
    @DisplayName("Проверка поиска")
    void loginVipUser() {

        step("Открываем главную страницу", () ->
                open("")
        );
        $(byText("Еще нет")).click();
        $(byText("Продолжить")).click();
        $("h2").shouldHave(text("Какая страховая подписка вас интересует?"));

        $(byText("Защита питомца")).click();
        $(byText("Продолжить")).click();
        $("h2").shouldHave(text("Давайте знакомиться!"));
    }
}
