package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@ExtendWith({AllureJunit5.class})
public class WelcomePageTest extends TestBase {

    @Test
    void welcomePageTest() {

        step("Открываем главную страницу", () ->
                open("")
        );
        step("Проверяем приветствие", () ->
                $("h1").shouldHave(Condition.text("Здравствуйте!"))
        );
        step("Проверяем описание", () ->
                $("h3").shouldHave(Condition.text("Вы уже регистрировались на нашем сайте или в приложении?"))
        );
        step("Проверяем дизейбл кнопки Продолжить", () ->
                $("h3").sibling(1).shouldHave(attribute("disabled"))
        );

        step("Нажимаем Да", () ->
                $("#id2").sibling(0).click()
        );

        step("Проверяем активную кнопку Продолжить", () ->
                $("h3").sibling(1).shouldNotHave(attribute("disabled"))
        );

    }
}
