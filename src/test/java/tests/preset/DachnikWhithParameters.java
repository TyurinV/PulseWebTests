package tests.preset;

import com.codeborne.selenide.Condition;
import config.Project;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class DachnikWhithParameters extends TestBase {
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
            $("input").setValue("9996666665");
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
                $("h2").shouldHave(text("Данные о вашем питомце"))
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

        step("Нажимаем на Посмотреть настройки подписки", () -> {
            $(byText("Посмотреть настройки подписки")).shouldHave(Condition.visible).click();
        });

        step("риск Pet_2 выбран", () -> {
            $("#Pet_2").parent().parent().parent().shouldHave(text("Заболевания\n" +
                            "Возместим средства, потраченные на лечение отравлений, непроходимостей, аллергий и других болезней")).
                    shouldHave(cssValue("border-top-color", "rgba(236, 104, 44, 1)"));
        });

        step("риск Pet_3 выбран", () -> {
            $("#Pet_3").parent().parent().parent().shouldHave(text("Укус клеща и опасные болезни\n" +
                            "Возместим расходы при устранении последствий укуса клеща и опасных инфекций: парагрипп, чума плотоядных, калицивироз и т. д.")).
                    shouldHave(cssValue("border-top-color", "rgba(236, 104, 44, 1)"));
        });


        step("риск Pet_1 НЕ выбран", () -> {
            $("#Pet_1").parent().parent().parent().shouldHave(text("Травмы\n" +
                            "Компенсируем стоимость лечения ран, порезов, ушибов, переломов и других травм")).
                    shouldHave(cssValue("border-top-color", "rgba(220, 225, 239, 1)"));
        });

        step("Текст Выбрано 2 риска", () -> {
            $(byText("Выбрано")).parent().shouldHave(text("Выбрано 2 риска"));
        });

        step("Жмем продолжить", () -> {
            $(byText("Продолжить")).click();
        });

        step("риск Pet_4 НЕ выбран", () -> {
            $("#Pet_4").parent().parent().parent().shouldHave(text("Непредвиденные расходы\n" +
                            "Покроем расходы на такси в ветклинику и обратно, а также услуг по выгулу питомца в случае болезни хозяина")).
                    shouldHave(cssValue("border-top-color", "rgba(220, 225, 239, 1)"));
        });

        step("риск Liability_2 НЕ выбран", () -> {
            $("#Liability_2").parent().parent().parent().shouldHave(text("Гражданская ответственность\n" +
                            "Компенсируем ущерб, нанесённый питомцем здоровью людей, животных или чужому имуществу")).
                    shouldHave(cssValue("border-top-color", "rgba(220, 225, 239, 1)"));
        });

        step("Текст Выбрано 2 риска", () -> {
            $(byText("Выбрано")).parent().shouldHave(text("Выбрано 2 риска"));
        });

        step("Жмем продолжить", () -> {
            $(byText("Продолжить")).click();
        });

        step("Дефолтное значение Pet_2 30 000 Р", () -> {
            $("[label=Заболевания]").$("div").$("input")
                    .shouldHave(value("30 000 ₽"));
        });

        step("Дефолтное значение Pet_3 15 000 ₽", () -> {
            $("[label='Укус клеща и опасные болезни']").$("div").$("input")
                    .shouldHave(value("15 000 ₽"));
        });

        step("Общая сумма возмещения = 45 000 ₽", () -> {
            $(byText("Общая сумма возмещения")).sibling(0).shouldHave(text("45 000 ₽"));
        });

        step("Жмем продолжить", () -> {
            $(byText("Применить")).click();
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
    }
}
