package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class VipTest extends TestBase{

    @Test
    @Tag("demowebshop")
    @Description("Проверка VIP")
    @DisplayName("Проверка VIP")
    void loginVipUser() {

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

        step("Ввод номера телефона", () -> {
            $("input").setValue("9997334958").pressEnter();
            $(byText("Получить код")).click();
        });

        step("Ввод смс кода", () -> {
        $("input", 0).setValue("00110");
        });

        step("Ввод фамилии", () -> {
        $("[name=lastName]").setValue("Тестиков");
        $(byText("Продолжить")).click();});

        step("Проверка финальной страницы", () -> {
        $("button").shouldHave(text("Скачать приложение"));
        $(byText("Поздравляем, Дмитрий!")).shouldBe(visible);
        $(withText("Как привилегированному клиенту СК «Пульс», вам доступны особые условия подписки" +
                " на защиту в нашем приложении «Пульс Жизни»")).shouldBe(visible);
        });
    }
}
