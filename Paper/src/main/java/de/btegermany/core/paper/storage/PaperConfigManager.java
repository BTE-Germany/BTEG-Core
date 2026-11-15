package de.btegermany.core.paper.storage;

import de.btegermany.core.common.storage.ConfigManager;
import de.btegermany.core.paper.setup.PaperPlugin;

import java.nio.file.Path;

public class PaperConfigManager extends ConfigManager {
    private final Path dataPath;

    public PaperConfigManager(PaperPlugin plugin) {
        this.dataPath = plugin.getDataPath();
    }

    public <T> T load(Class<T> configClass, String fileName) {
        return load(configClass, this.dataPath.resolve(fileName));
    }
}
