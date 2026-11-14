package de.btegermany.core.common.storage;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.util.HashMap;
import java.util.Map;

//TODO: @ConfigSerializable ?
public class ConfigManager {
    private final Map<String, ConfigurationLoader<CommentedConfigurationNode>> loaders = new HashMap<>();
    private final Map<String, CommentedConfigurationNode> rootNodes = new HashMap<>();

    public synchronized void addLoader(String name, ConfigurationLoader<CommentedConfigurationNode> loader) {
        this.loaders.put(name, loader);
    }

    public synchronized CommentedConfigurationNode get(String loaderName) {
        if (!this.rootNodes.containsKey(loaderName)) {
            this.reload(loaderName);
        }

        return this.rootNodes.get(loaderName);
    }

    //TODO: benötigt? Also echtes reload, initiales load natürlich schon
    public synchronized void reload(String loaderName) {
        try {
            ConfigurationLoader<CommentedConfigurationNode> loader = this.loaders.get(loaderName);
            CommentedConfigurationNode rootNode = loader.load();
            this.rootNodes.put(loaderName, rootNode);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void save(String loaderName) {
        try {
            ConfigurationLoader<CommentedConfigurationNode> loader = this.loaders.get(loaderName);
            CommentedConfigurationNode rootNode = this.get(loaderName);
            loader.save(rootNode);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

}
