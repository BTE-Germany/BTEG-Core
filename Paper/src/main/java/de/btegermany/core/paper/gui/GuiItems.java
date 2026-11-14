package de.btegermany.core.paper.gui;

import de.btegermany.core.paper.gui.base.CustomGui;
import de.btegermany.core.paper.item.ItemGenerator;
import dev.triumphteam.gui.guis.GuiItem;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class GuiItems {
    private final ItemGenerator itemGenerator;

    public GuiItem closeItem(CustomGui<?> gui) {
        return emptyItem(
                "Schließen",
                NamedTextColor.DARK_RED,
                gui::close
        );
    }

    public GuiItem blankItem() {
        return new GuiItem(this.itemGenerator.customModel(
                "blank",
                Component.empty()
        ));
    }

    public GuiItem emptyItem(String name, TextColor textColor, Runnable onClick) {
        return new GuiItem(this.itemGenerator.customModel(
                "empty",
                Component.text(name, textColor)
        ), _ -> onClick.run());
    }

    public static GuiItem fillerItem() {
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.customName(Component.text(""));
        item.setItemMeta(meta);
        return new GuiItem(item);
    }

}
