package de.btegermany.core.paper.setup;

import co.aikar.commands.PaperCommandManager;
import de.btegermany.core.paper.Utils;
import de.btegermany.core.paper.messaging.PaperMessenger;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class PaperPlugin extends JavaPlugin {
    protected PaperPluginSetup setup;

    @Override
    public void onEnable() {
        this.setup = new PaperPluginSetup(this);
    }

    @Override
    public void onDisable() {
        this.setup.onDisable();
    }

    public PaperCommandManager getCommandManager() {
        return this.setup.getCommandManager();
    }

    public PaperMessenger getMessenger() {
        return this.setup.getMessenger();
    }

    public Utils getUtils() {
        return this.setup.getUtils();
    }

}
