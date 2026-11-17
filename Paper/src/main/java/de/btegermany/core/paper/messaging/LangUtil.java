package de.btegermany.core.paper.messaging;

import de.btegermany.core.paper.setup.PaperPlugin;
import li.cinnazeyy.langlibs.core.LangLibAPI;
import li.cinnazeyy.langlibs.core.file.LanguageFile;
import li.cinnazeyy.langlibs.core.language.Language;
import li.cinnazeyy.langlibs.core.language.LanguageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class LangUtil extends LanguageUtil {
    private static LangUtil langUtilInstance;

    public static void init(PaperPlugin plugin) {
        if (langUtilInstance != null) return;
        LangLibAPI.register(plugin, new LanguageFile[]{
                new LanguageFile(plugin, 1.0, Language.en_GB),
                new LanguageFile(plugin, 1.0, Language.de_DE, "de_AT", "de_CH")
        });
        langUtilInstance = new LangUtil(plugin);
    }

    public LangUtil(PaperPlugin plugin) {
        super(plugin);
    }

    public static LangUtil getInstance() {
        return langUtilInstance;
    }

    @Override
    public String get(CommandSender sender, String key) {
        return super.get(sender, key);
    }
}
