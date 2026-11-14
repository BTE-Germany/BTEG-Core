package de.btegermany.core.velocity.setup;

import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.ChannelRegistrar;
import de.btegermany.core.common.setup.Disableable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PluginChannelsSetup implements Disableable {
    private final ChannelRegistrar channelRegistrar;
    private final Set<ChannelIdentifier> registeredChannels = new HashSet<>();

    public PluginChannelsSetup(VelocityPluginSetup pluginSetup) {
        this.channelRegistrar = pluginSetup.getPlugin().getProxyServer().getChannelRegistrar();
    }

    public PluginChannelsSetup register(ChannelIdentifier... channelIdentifiers) {
        this.channelRegistrar.register(channelIdentifiers);
        this.registeredChannels.addAll(Arrays.asList(channelIdentifiers));
        return this;
    }

    @Override
    public void onDisable() {
        this.channelRegistrar.unregister(this.registeredChannels.toArray(ChannelIdentifier[]::new));
    }
}
