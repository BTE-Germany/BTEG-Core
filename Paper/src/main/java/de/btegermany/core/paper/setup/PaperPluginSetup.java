package de.btegermany.core.paper.setup;

import co.aikar.commands.PaperCommandManager;
import de.btegermany.core.common.setup.CommandsSetup;
import de.btegermany.core.common.setup.MessagingSetup;
import de.btegermany.core.common.setup.PluginSetup;
import de.btegermany.core.common.utils.Utils;
import de.btegermany.core.paper.completion.CoreCompletions;
import de.btegermany.core.paper.messaging.PaperMessenger;
import de.btegermany.core.paper.messaging.PaperTranslationProvider;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.function.BiConsumer;

@Getter(AccessLevel.PACKAGE)
public class PaperPluginSetup extends PluginSetup {
    private final PaperPlugin plugin;
    private final PaperCommandManager commandManager;
    private PaperTranslationProvider translationProvider = null;
    private PaperMessenger messenger = null;
    private Utils utils = null;

    public PaperPluginSetup(PaperPlugin plugin) {
        this.plugin = plugin;
        this.commandManager = new PaperCommandManager(plugin);
    }

    public PaperPluginSetup pluginChannels(BiConsumer<PaperPluginSetup, PluginChannelsSetup> consumer) {
        executeStep(consumer, this, new PluginChannelsSetup(this));
        return this;
    }

    public PaperPluginSetup events(BiConsumer<PaperPluginSetup, EventsSetup> consumer) {
        executeStep(consumer, this, new EventsSetup(this));
        return this;
    }

    public PaperPluginSetup commands(BiConsumer<PaperPluginSetup, CommandsSetup<PaperCommandManager>> consumer) {
        executeStep(consumer, this, new CommandsSetup<>(this.commandManager, this.getDefaultCompletions()));
        return this;
    }

    public PaperPluginSetup messaging(BiConsumer<PaperPluginSetup, MessagingSetup> consumer) {
        MessagingSetup messagingSetup = new MessagingSetup();
        executeStep(consumer, this, messagingSetup);

        this.translationProvider = new PaperTranslationProvider(plugin, messagingSetup.buildMiniMessage());
        this.messenger = new PaperMessenger(this.translationProvider);
        return this;
    }

    public PaperPluginSetup utils(BiConsumer<PaperPluginSetup, PaperUtilsSetup> consumer) {
        PaperUtilsSetup utilsSetup = new PaperUtilsSetup();
        executeStep(consumer, this, utilsSetup);

        this.utils = utilsSetup.getUtils();
        return this;
    }

    private Object[] getDefaultCompletions() {
        return new Object[]{
                new CoreCompletions(this.plugin)
        };
    }

}
