package de.btegermany.core.velocity.storage;

import de.btegermany.core.common.storage.ConfigManager;
import de.btegermany.core.velocity.setup.VelocityPlugin;

import java.nio.file.Path;

public class VelocityConfigManager extends ConfigManager {
    private final Path dataPath;

    public VelocityConfigManager(VelocityPlugin plugin) {
        this.dataPath = plugin.getDataDirectoryPath();
    }

    public <T> T load(Class<T> configClass, String fileName) {
        return load(configClass, this.dataPath.resolve(fileName));
    }
}
