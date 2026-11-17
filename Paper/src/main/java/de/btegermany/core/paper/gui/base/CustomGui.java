package de.btegermany.core.paper.gui.base;

import de.btegermany.core.paper.messaging.PaperTranslationContext;
import de.btegermany.core.paper.setup.PaperPlugin;
import dev.triumphteam.gui.guis.BaseGui;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CustomGui<G extends BaseGui> {
    protected final PaperPlugin plugin;
    @Getter
    protected final Player player;
    protected final G gui;
    //TODO: falls notwendig umfassenderen PlayerContext? Mit u.a. translation, ...
    @Getter
    protected final PaperTranslationContext translationContext;

    public CustomGui(PaperPlugin plugin, Player player, G gui) {
        this.plugin = plugin;
        this.player = player;
        this.gui = gui;
        this.translationContext = plugin.getTranslationProvider().createContext(player);
    }

    public void open() {
        Bukkit.getScheduler().runTask(this.plugin, () -> this.gui.open(this.player));
    }

    public void close() {
        Bukkit.getScheduler().runTask(this.plugin, () -> this.gui.close(this.player));
    }
}
