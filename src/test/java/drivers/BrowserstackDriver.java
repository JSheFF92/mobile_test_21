package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    static BrowserstackConfig browserstack = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", browserstack.getUser());
        mutableCapabilities.setCapability("browserstack.key", browserstack.getKey());

        mutableCapabilities.setCapability("app", browserstack.getApp());

        mutableCapabilities.setCapability("device", browserstack.getDevice());
        mutableCapabilities.setCapability("os_version", browserstack.getVersion());

        mutableCapabilities.setCapability("project", browserstack.getProject());
        mutableCapabilities.setCapability("build", browserstack.getBuild());
        mutableCapabilities.setCapability("name", browserstack.getName());

        try {
            return new RemoteWebDriver(
                    new URL(browserstack.getUrlRemoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
