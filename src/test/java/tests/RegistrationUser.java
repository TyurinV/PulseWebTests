package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationUser {

    @Test
    @Description("Проверка поиска")
    @DisplayName("Проверка поиска")
    void loginVipUser() {
        open("https://web-lk-pulse-dev.website.cloud.croc.ru/");
        $(byText("Еще нет")).click();
        $(byText("Продолжить")).click();
        $("h2").shouldHave(text("Какая страховая подписка вас интересует?"));

        $(byText("Защита питомца")).click(); //попробывать byValue
        $(byText("Продолжить")).click();
        $("h2").shouldHave(text("Давайте знакомиться!"));




        sleep(5000);
    }
}
