package de.btegermany.core.paper.completion;

import co.aikar.commands.BukkitCommandCompletionContext;
import de.btegermany.core.common.CompareUtils;
import de.btegermany.core.common.completion.AsyncCompletion;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * Provides all players' names (not only visible like default completion). Useful for e.g. moderation commands
 */
@RequiredArgsConstructor
public final class CoreCompletions {
    private final JavaPlugin plugin;

    @AsyncCompletion(id = "allplayers")
    public Collection<String> allPlayers(BukkitCommandCompletionContext context) {
        return this.plugin.getServer().getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> CompareUtils.startsWithIgnoreCase(name, context.getInput()))
                .toList();
    }

}