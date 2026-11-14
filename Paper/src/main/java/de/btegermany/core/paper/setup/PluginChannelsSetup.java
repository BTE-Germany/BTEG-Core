package de.btegermany.core.paper.setup;

import de.btegermany.core.common.setup.Disableable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginChannelsSetup implements Disableable {
    private final JavaPlugin plugin;
    private final Messenger messenger;

    public PluginChannelsSetup(PaperPluginSetup pluginSetup) {
        JavaPlugin plugin = pluginSetup.getPlugin();
        this.plugin = plugin;
        this.messenger = plugin.getServer().getMessenger();
    }

    public PluginChannelsSetup incoming(String channel, PluginMessageListener listener) {
        this.messenger.registerIncomingPluginChannel(this.plugin, channel, listener);
        return this;
    }

    public PluginChannelsSetup outgoing(String channel) {
        this.messenger.registerOutgoingPluginChannel(this.plugin, channel);
        return this;
    }

    public PluginChannelsSetup inOut(String channel, PluginMessageListener listener) {
        this.incoming(channel, listener);
        this.outgoing(channel);
        return this;
    }

    @Override
    public void onDisable() {
        this.messenger.unregisterIncomingPluginChannel(this.plugin);
        this.messenger.unregisterOutgoingPluginChannel(this.plugin);
    }

}
