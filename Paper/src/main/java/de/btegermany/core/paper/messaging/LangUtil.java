package de.btegermany.core.paper.messaging;

import li.cinnazeyy.langlibs.core.LangLibAPI;
import li.cinnazeyy.langlibs.core.file.LanguageFile;
import li.cinnazeyy.langlibs.core.language.Language;
import li.cinnazeyy.langlibs.core.language.LanguageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

class LangUtil extends LanguageUtil {

    public LangUtil(Plugin plugin) {
        super(plugin);

        LangLibAPI.register(plugin, new LanguageFile[]{
                new LanguageFile(plugin, 1.0, Language.en_GB),
                new LanguageFile(plugin, 1.0, Language.de_DE, "de_AT", "de_CH")
        });
    }

    @Override
    public String get(CommandSender sender, String key) {
        return super.get(sender, key);
    }
}
