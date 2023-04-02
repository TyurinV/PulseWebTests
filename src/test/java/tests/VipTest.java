package tests;

import config.Project;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@ExtendWith({AllureJunit5.class})
public class VipTest extends TestBase{

    @Test
    @Description("Check e2e for vip")
    @DisplayName("e2e vip user")
    @Story("Полное флоу поведения ВИП пользователя")
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
            $("input").setValue(Project.config.loginVip()).pressEnter();
            $(byText("Получить код")).click();
        });

        step("Ввод смс кода", () -> {
        $("input", 0).setValue("00110");
        });

        step("Ввод фамилии", () -> {
        $("[name=lastName]").setValue(Project.config.secondName());
        $(byText("Продолжить")).click();});

        step("Проверка финальной страницы", () -> {
        $("button").shouldHave(text("Скачать приложение"));
        $(byText("Поздравляем, Владимир!")).shouldBe(visible);
        $(withText("Как привилегированному клиенту СК «Пульс», вам доступны особые условия подписки" +
                " на защиту в нашем приложении «Пульс Жизни»")).shouldBe(visible);
        });
    }
}
