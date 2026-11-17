package de.btegermany.core.paper.messaging;

import de.btegermany.core.common.messaging.TranslationContext;
import org.bukkit.command.CommandSender;

public class PaperTranslationContext extends TranslationContext<CommandSender> {
    public PaperTranslationContext(CommandSender sender, PaperTranslationProvider translationProvider) {
        super(sender, translationProvider);
    }
}
