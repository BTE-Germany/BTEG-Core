package de.btegermany.core.paper.gui.base;

import dev.triumphteam.gui.guis.BaseGui;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class CustomGui<G extends BaseGui> {
    protected final JavaPlugin plugin;
    @Getter
    protected final Player player;
    protected final G gui;

    public void open() {
        Bukkit.getScheduler().runTask(this.plugin, () -> this.gui.open(this.player));
    }

    public void close() {
        Bukkit.getScheduler().runTask(this.plugin, () -> this.gui.close(this.player));
    }

}
