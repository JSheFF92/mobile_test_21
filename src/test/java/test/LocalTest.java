package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalTest extends TestBase {

    @Test
    @Tag("local")
    void successfulSearchTest() {
        back();
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("local")
    @DisplayName("Тест 4 стартовых страниц wiki")
    void fourPages() {
        step("Проверка текста на первой странице", () -> {
            assertThat($(By.id("org.wikipedia.alpha:id/primaryTextView")).getText())
                    .isEqualTo("The Free Encyclopedia\n" +
                            "…in over 300 languages");
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Проверка текста на второй странице", () -> {
            assertThat($(By.id("org.wikipedia.alpha:id/primaryTextView")).getText())
                    .isEqualTo("New ways to explore");
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Проверка текста на третьей странице", () -> {
            assertThat($(By.id("org.wikipedia.alpha:id/primaryTextView")).getText())
                    .isEqualTo("Reading lists with sync");
            $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Проверка текста на четвертой странице и нажатие на кнопку принятия условий", () -> {
            assertThat($(By.id("org.wikipedia.alpha:id/primaryTextView")).getText())
                    .isEqualTo("Send anonymous data");
            $(By.id("org.wikipedia.alpha:id/acceptButton")).click();
        });
    }
}