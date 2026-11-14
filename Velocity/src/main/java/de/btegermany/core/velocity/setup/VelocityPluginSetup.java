package de.btegermany.core.velocity.setup;

import co.aikar.commands.VelocityCommandManager;
import de.btegermany.core.common.setup.CommandsSetup;
import de.btegermany.core.common.setup.MessagingSetup;
import de.btegermany.core.common.setup.PluginSetup;
import de.btegermany.core.velocity.Utils;
import de.btegermany.core.velocity.messaging.VelocityMessenger;
import de.btegermany.core.velocity.messaging.VelocityTranslationProvider;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.function.Consumer;

@Getter(AccessLevel.PACKAGE)
public class VelocityPluginSetup extends PluginSetup {
    private final VelocityPlugin plugin;
    private final VelocityCommandManager commandManager;
    private VelocityMessenger messenger = null;
    private Utils utils = null;

    VelocityPluginSetup(VelocityPlugin plugin) {
        this.plugin = plugin;
        this.commandManager = new VelocityCommandManager(plugin.getProxyServer(), plugin);
    }

    public VelocityPluginSetup pluginChannels(Consumer<PluginChannelsSetup> consumer) {
        executeStep(consumer, new PluginChannelsSetup(this));
        return this;
    }

    public VelocityPluginSetup events(Consumer<EventsSetup> consumer) {
        executeStep(consumer, new EventsSetup(this));
        return this;
    }

    public VelocityPluginSetup commands(Consumer<CommandsSetup<VelocityCommandManager>> consumer) {
        executeStep(consumer, new CommandsSetup<>(this.commandManager));
        return this;
    }

    public VelocityPluginSetup messaging(Consumer<MessagingSetup> consumer) {
        MessagingSetup messagingSetup = new MessagingSetup();
        executeStep(consumer, messagingSetup);

        this.messenger = new VelocityMessenger(
                messagingSetup.buildMiniMessage(),
                new VelocityTranslationProvider()
        );
        return this;
    }

    public VelocityPluginSetup utils(Consumer<UtilsSetup> consumer) {
        UtilsSetup utilsSetup = new UtilsSetup(this.plugin);
        executeStep(consumer, utilsSetup);

        this.utils = utilsSetup.buildUtils();
        return this;
    }
}