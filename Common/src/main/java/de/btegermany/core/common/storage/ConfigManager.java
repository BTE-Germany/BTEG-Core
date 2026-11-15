package de.btegermany.core.common.storage;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.loader.AbstractConfigurationLoader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private final Map<Class<?>, AbstractConfigurationLoader<CommentedConfigurationNode>> loaders = new HashMap<>();
    private final Map<Class<?>, CommentedConfigurationNode> rootNodes = new HashMap<>();

    public <T> T load(Class<T> configClass, Path configPath) {
        String fileName = configPath.toFile().getName();
        AbstractConfigurationLoader<CommentedConfigurationNode> loader;

        if (fileName.endsWith(".conf")) {
            loader = DefaultConfigLoaders.hocon(configPath);
        } else if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            loader = DefaultConfigLoaders.yaml(configPath);
        } else {
            throw new RuntimeException("No default loader available for config file '%s'".formatted(configPath.getFileName()));
        }

        return this.load(configClass, loader);
    }

    public synchronized <T> T load(Class<T> configClass, AbstractConfigurationLoader<CommentedConfigurationNode> loader) {
        try {
            CommentedConfigurationNode root = loader.load();
            T config = root.get(configClass);
            if (config == null) {
                throw new RuntimeException("Failed to load config '%s'".formatted(configClass.toString()));
            }

            this.loaders.put(configClass, loader);
            this.rootNodes.put(configClass, root);
            return config;
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized <T> void save(T config) {
        Class<T> configClass = this.getClassCasted(config);

        try {
            CommentedConfigurationNode root = this.rootNodes.get(configClass);
            root.set(config);
            this.loaders.get(configClass).save(root);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> getClassCasted(T config) {
        return (Class<T>) config.getClass();
    }

}
