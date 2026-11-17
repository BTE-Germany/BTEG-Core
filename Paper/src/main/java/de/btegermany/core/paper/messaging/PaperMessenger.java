package de.btegermany.core.paper.messaging;

import de.btegermany.core.common.messaging.Messenger;
import org.bukkit.command.CommandSender;

public class PaperMessenger extends Messenger<CommandSender> {

    public PaperMessenger(PaperTranslationProvider translationProvider) {
        super(translationProvider);
    }

}
