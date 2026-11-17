package de.btegermany.core.paper.messaging;

import de.btegermany.core.common.messaging.TranslationProvider;
import de.btegermany.core.paper.setup.PaperPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class PaperTranslationProvider extends TranslationProvider<CommandSender, PaperTranslationContext> {
    public PaperTranslationProvider(PaperPlugin plugin, MiniMessage miniMessage) {
        super(miniMessage);

        LangUtil.init(plugin);
    }

    @Override
    public String getRaw(CommandSender sender, String key) {
        return LangUtil.getInstance().get(sender, key);
    }

    @Override
    public PaperTranslationContext createContext(CommandSender sender) {
        return new PaperTranslationContext(sender, this);
    }
}
