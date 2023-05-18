package tests.preset;

import com.codeborne.selenide.Condition;
import config.Project;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@ExtendWith({AllureJunit5.class})
public class Dachnik extends TestBase {

    String pet = "Кошка",
            breed = "Русская голубая",
            age = "15 лет",
            sex = "Самец",
            name = "Шарик";


    @Test
    void checkDanikPreset() {
        step("Открываем главную страницу?", () ->
                open("?product=pets&preset=dachnik&personalization=4-paws_base")
        );

        step("Ввод ошибочный номер телефона", () -> {
            $("input").setValue("8884221581");
            $(byText("Получить код")).click();
        });

        step("Ввод кода", () -> {
            $("input", 0).setValue("00110");
        });

        step("Ввод фамилии", () -> {
            $("[name=lastName]").setValue(Project.config.secondName());
            $(byText("Продолжить")).click();
        });
        //на этом шаге идёт проверка странице всей. может вынести в page object?
        step("Проверяем заголовок", () ->
                $("h2").shouldHave(Condition.text("Данные о вашем питомце"))
        );
        //Тут делаем проверку, что можно выбрать только кошку и зсобаку и выбираем Кошку
        step("Проверка выпадающего списка Вид питомца и выбирем " + pet, () -> {
            $("#listbox-input--1").click();
            $(byText(pet)).ancestor("ul").$$("li")
                    .shouldHave(size(2)).shouldHave(texts("Кошка", "Собака"));
            $(byText(pet)).click();
        });

        step("Выбираем породу " + breed, () -> {
            $("[label=Порода]").click();
            $(byText(breed)).click();
        });

        step("Выбираем возраст " + age, () -> {
            $("#button--listbox-input--2").click();
            $(byText(age)).click();
        });

        step("Выбираем пол " + sex, () -> {
            $("#button--listbox-input--3").click();
            $(byText(sex)).click();
        });

        step("Вводем имя" + name, () -> {
            $("[name=name]").setValue(name);
        });

        step("Жмем продолжить", () -> {
            $(byText("Продолжить")).click();
        });

        step("Проверяем заголовог Шаг 2", () -> {
            $(byText("Шаг 2")).shouldBe(Condition.visible);
        });
        sleep(3000);// get-prices долго грузится страница
        step("Жмем продолжить второй раз", () -> {
            $(byText("Продолжить")).click();
        });

        step("Проверка набора рисков, суммы и т.д.", () -> {
            $(byText("Питомец")).sibling(0).shouldHave(Condition.text(pet + " • " + sex + " • " + age));
            $(byText("Порода")).sibling(0).shouldHave(Condition.text(breed));
            $(byText("Как зовут питомца")).sibling(0).shouldHave(Condition.text(name));
            $(byText("Основные риски")).sibling(0).shouldHave(Condition.text("Заболевания, укус клеща и опасные болезни"));
            $(byText("Дополнительные риски")).sibling(0).shouldHave(Condition.text("Не выбраны"));
            $(byText("Максимальная сумма возмещения")).sibling(0).shouldHave(Condition.text("45 000 ₽"));
        });
        //todo max case e2e
    }
}
