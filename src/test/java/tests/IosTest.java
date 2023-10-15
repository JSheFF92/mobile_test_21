package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

public class IosTest extends TestBase {

    @Test
    @Tag("ios")
    @DisplayName("Поиск статей на Wiki")
    void successfulSearchTest() {
        step("click button Text", () -> {
            $(id("Text Button")).click();
        });

        step("Click on the field 'Enter a text'", () -> {
            $(id("Text Input")).click();
        });

        step("Send value in 'Enter a text' and send Enter", () -> {
            $(id("Text Input")).sendKeys("Mobile IOS Test");
            $(id("Text Input")).pressEnter();
        });

        step("Check result", () -> {
            assertThat($(id("Text Output")).getText())
                    .isEqualTo("Mobile IOS Test");
        });
    }
}