package de.btegermany.core.velocity.setup;

import co.aikar.commands.VelocityCommandManager;
import de.btegermany.core.common.setup.CommandsSetup;
import de.btegermany.core.common.setup.MessagingSetup;
import de.btegermany.core.common.setup.PluginSetup;
import de.btegermany.core.common.utils.Utils;
import de.btegermany.core.velocity.messaging.VelocityMessenger;
import de.btegermany.core.velocity.messaging.VelocityTranslationProvider;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.function.BiConsumer;

@Getter(AccessLevel.PACKAGE)
public class VelocityPluginSetup extends PluginSetup {
    private final VelocityPlugin plugin;
    private final VelocityCommandManager commandManager;
    private VelocityTranslationProvider translationProvider = null;
    private VelocityMessenger messenger = null;
    private Utils utils = null;

    VelocityPluginSetup(VelocityPlugin plugin) {
        this.plugin = plugin;
        this.commandManager = new VelocityCommandManager(plugin.getProxyServer(), plugin);
    }

    public VelocityPluginSetup pluginChannels(BiConsumer<VelocityPluginSetup, PluginChannelsSetup> consumer) {
        executeStep(consumer, this, new PluginChannelsSetup(this));
        return this;
    }

    public VelocityPluginSetup events(BiConsumer<VelocityPluginSetup, EventsSetup> consumer) {
        executeStep(consumer, this, new EventsSetup(this));
        return this;
    }

    public VelocityPluginSetup commands(BiConsumer<VelocityPluginSetup, CommandsSetup<VelocityCommandManager>> consumer) {
        executeStep(consumer, this, new CommandsSetup<>(this.commandManager));
        return this;
    }

    public VelocityPluginSetup messaging(BiConsumer<VelocityPluginSetup, MessagingSetup> consumer) {
        MessagingSetup messagingSetup = new MessagingSetup();
        executeStep(consumer, this, messagingSetup);

        this.translationProvider = new VelocityTranslationProvider(messagingSetup.buildMiniMessage());
        this.messenger = new VelocityMessenger(this.translationProvider);
        return this;
    }

    public VelocityPluginSetup utils(BiConsumer<VelocityPluginSetup, VelocityUtilsSetup> consumer) {
        VelocityUtilsSetup utilsSetup = new VelocityUtilsSetup(this.plugin);
        executeStep(consumer, this, utilsSetup);

        this.utils = utilsSetup.getUtils();
        return this;
    }
}