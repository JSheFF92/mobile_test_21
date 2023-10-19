package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    public static String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    static void beforeAll() {
        if (deviceHost.equals("browserstack")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        }
        if (deviceHost.equals("emulation")) {
            Configuration.browser = LocalDriver.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        if (deviceHost.equals("browserstack")) {
            Attach.pageSource();

            String sessionId = Selenide.sessionId().toString();
            closeWebDriver();

            Attach.addVideo(sessionId);
        }
        if(deviceHost.equals("emulation")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();

            closeWebDriver();
        }
    }
}
