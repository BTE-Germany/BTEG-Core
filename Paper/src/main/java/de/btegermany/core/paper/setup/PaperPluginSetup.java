package de.btegermany.core.paper.setup;

import co.aikar.commands.PaperCommandManager;
import de.btegermany.core.common.setup.CommandsSetup;
import de.btegermany.core.common.setup.MessagingSetup;
import de.btegermany.core.common.setup.PluginSetup;
import de.btegermany.core.paper.Utils;
import de.btegermany.core.paper.completion.CoreCompletions;
import de.btegermany.core.paper.messaging.PaperMessenger;
import de.btegermany.core.paper.messaging.PaperTranslationProvider;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.function.Consumer;

@Getter(AccessLevel.PACKAGE)
public class PaperPluginSetup extends PluginSetup {
    private final PaperPlugin plugin;
    private final PaperCommandManager commandManager;
    private PaperMessenger messenger = null;
    private Utils utils = null;

    public PaperPluginSetup(PaperPlugin plugin) {
        this.plugin = plugin;
        this.commandManager = new PaperCommandManager(plugin);
    }

    public PaperPluginSetup pluginChannels(Consumer<PluginChannelsSetup> consumer) {
        executeStep(consumer, new PluginChannelsSetup(this));
        return this;
    }

    public PaperPluginSetup events(Consumer<EventsSetup> consumer) {
        executeStep(consumer, new EventsSetup(this));
        return this;
    }

    public PaperPluginSetup commands(Consumer<CommandsSetup<PaperCommandManager>> consumer) {
        executeStep(consumer, new CommandsSetup<>(this.commandManager, this.getDefaultCompletions()));
        return this;
    }

    public PaperPluginSetup messaging(Consumer<MessagingSetup> consumer) {
        MessagingSetup messagingSetup = new MessagingSetup();
        executeStep(consumer, messagingSetup);

        this.messenger = new PaperMessenger(
            messagingSetup.buildMiniMessage(),
            new PaperTranslationProvider(this.plugin)
        );
        return this;
    }

    public PaperPluginSetup utils(Consumer<UtilsSetup> consumer) {
        UtilsSetup utilsSetup = new UtilsSetup();
        executeStep(consumer, utilsSetup);

        this.utils = utilsSetup.buildUtils();
        return this;
    }

    private Object[] getDefaultCompletions() {
        return new Object[]{
                new CoreCompletions(this.plugin)
        };
    }

}
