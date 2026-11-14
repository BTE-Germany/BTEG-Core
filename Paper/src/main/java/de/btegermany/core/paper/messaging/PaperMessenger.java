package de.btegermany.core.paper.messaging;

import de.btegermany.core.common.messaging.Messenger;
import de.btegermany.core.common.messaging.TranslationProvider;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class PaperMessenger extends Messenger<CommandSender> {

    public PaperMessenger(MiniMessage miniMessage, TranslationProvider<CommandSender> translationProvider) {
        super(miniMessage, translationProvider);
    }

}
