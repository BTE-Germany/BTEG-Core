package de.btegermany.core.velocity.setup;

import com.velocitypowered.api.event.EventManager;

public class EventsSetup {
    private final EventManager eventManager;
    private final VelocityPlugin plugin;

    public EventsSetup(VelocityPluginSetup pluginSetup) {
        VelocityPlugin plugin = pluginSetup.getPlugin();
        this.eventManager = plugin.getProxyServer().getEventManager();
        this.plugin = plugin;
    }

    public EventsSetup listeners(Object... listeners) {
        for (Object listener : listeners) {
            this.registerListener(listener);
        }
        return this;
    };

    private void registerListener(Object listener) {
        this.eventManager.register(this.plugin, listener);
    }

}
