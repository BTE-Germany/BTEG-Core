package de.btegermany.core.paper.messaging;

import de.btegermany.core.common.messaging.TranslationProvider;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class PaperTranslationProvider implements TranslationProvider<CommandSender> {
    private final LangUtil langUtil;

    public PaperTranslationProvider(Plugin plugin) {
        this.langUtil = new LangUtil(plugin);
    }

    @Override
    public String get(CommandSender sender, String key) {
        return this.langUtil.get(sender, key);
    }

}
