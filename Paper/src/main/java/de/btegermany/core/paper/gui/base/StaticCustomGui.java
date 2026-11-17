package de.btegermany.core.paper.gui.base;

import de.btegermany.core.paper.gui.GuiItems;
import de.btegermany.core.paper.gui.GuiTitle;
import de.btegermany.core.paper.setup.PaperPlugin;
import dev.triumphteam.gui.guis.Gui;
import org.bukkit.entity.Player;

import java.util.List;

public class StaticCustomGui extends CustomGui<Gui> {

    public StaticCustomGui(GuiTitle title, int rows, Player player, PaperPlugin plugin) {
        super(plugin, player, Gui.gui()
                .title(title.getComponent())
                .rows(rows)
                .disableAllInteractions()
                .create()
        );

        GuiItems guiItems = plugin.getUtils(GuiItems.class);
        this.gui.setItem(List.of(0, 1, 2, 3, 4, 5, 6, 7), guiItems.blankItem());
        this.gui.setItem(8, guiItems.closeItem(this));
    }

}
