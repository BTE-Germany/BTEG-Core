package de.btegermany.core.paper.setup;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsSetup {

    private final JavaPlugin plugin;
    private final PluginManager pluginManager;

    public EventsSetup(PaperPluginSetup pluginSetup) {
        JavaPlugin plugin = pluginSetup.getPlugin();
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
    }

    public EventsSetup listeners(Listener... listeners) {
        for (Listener listener : listeners) {
           this.registerListener(listener);
        }
        return this;
    };

    private void registerListener(Listener listener) {
        this.pluginManager.registerEvents(listener, this.plugin);
    }

}
