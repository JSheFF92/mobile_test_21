package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MobileConfig mobile = ConfigFactory.create(MobileConfig.class, System.getProperties());
        AuthConfig auth = ConfigFactory.create(AuthConfig.class, System.getProperties());

        MutableCapabilities mutableCapabilities = new MutableCapabilities();

        // Set your access credentials
//        mutableCapabilities.setCapability("browserstack.user", "yoursheff1");
//        mutableCapabilities.setCapability("browserstack.key", "zcq5LUf4gRAzYhd6FVsx");

        mutableCapabilities.setCapability("browserstack.user", auth.getUser());
        mutableCapabilities.setCapability("browserstack.key", auth.getKey());

        //userName: yoursheff1
        //accessKey: zcq5LUf4gRAzYhd6FVsx

        // Set URL of the application under test
//        mutableCapabilities.setCapability("app", "bs://sample.app");
        mutableCapabilities.setCapability("app", mobile.getApp());

        // Specify device and os_version for testing
//        mutableCapabilities.setCapability("device", "Google Pixel 3");
//        mutableCapabilities.setCapability("os_version", "9.0");


//        mutableCapabilities.setCapability("device", "iPhone 14 Pro Max");
//        mutableCapabilities.setCapability("os_version", "16.0");

        mutableCapabilities.setCapability("device", mobile.getDevice());
        mutableCapabilities.setCapability("os_version", mobile.getVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "BrowserStack Sample");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
//                    new URL("https://hub.browserstack.com/wd/hub"), mutableCapabilities);
                    new URL(auth.getRemoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
