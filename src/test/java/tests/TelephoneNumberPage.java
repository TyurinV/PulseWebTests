package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@ExtendWith({AllureJunit5.class})
public class TelephoneNumberPage extends TestBase {

    @Test
    void WrongPhoneNumber(){
        step("Открываем главную страницу", () ->
                open("https://web-lk-ift.website.cloud.croc.ru/")
        );

        step("Есть подпска выбираем", () -> {
            $(byText("Да")).click();
            $(byText("Продолжить")).click();
        });

        step("Выбирем спорт", () -> {
            $(withText("спорта")).click();
            $(byText("Продолжить")).click();
        });

        step("Ввод ошибочный номер телефона", () -> {
            $("input").setValue("1111111111").pressEnter();
            $(byText("Получить код")).click();
        });

        step("Отображение сообщения об ошибке - Неверный номер телефона ", () -> {
        $("[type=tel]").parent().sibling(0).shouldHave(Condition.text("Неверный номер телефона"));
        });
    }
}
