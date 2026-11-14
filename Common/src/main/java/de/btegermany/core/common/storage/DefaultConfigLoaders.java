package de.btegermany.core.common.storage;

import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Callable;
import java.util.function.UnaryOperator;

public class DefaultConfigLoaders {

    public static HoconConfigurationLoader hocon(Path configPath) {
        return HoconConfigurationLoader.builder()
                .path(configPath)

                // fixt /event set BUG-0055 ("values.conf: Device or resource busy")
                .defaultOptions(defaultOptions())
                //TODO: remove?
                .emitJsonCompatible(emitJsonCompatible())
                .sink(sink(configPath))

                .build();
    }

    public static YamlConfigurationLoader yaml(Path configPath) {
        return YamlConfigurationLoader.builder()
                .path(configPath)

                .defaultOptions(defaultOptions())
                .sink(sink(configPath))

                .build();
    }

    private static UnaryOperator<ConfigurationOptions> defaultOptions() {
        return opts -> opts.shouldCopyDefaults(true);
    }

    private static boolean emitJsonCompatible() {
        return false;
    }

    private static Callable<BufferedWriter> sink(Path configPath) {
        return () -> Files.newBufferedWriter(configPath, StandardOpenOption.WRITE,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

}
