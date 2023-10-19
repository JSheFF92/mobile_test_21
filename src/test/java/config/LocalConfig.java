package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/emulator.properties"
})

public interface LocalConfig extends Config {

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getVersion();

}
