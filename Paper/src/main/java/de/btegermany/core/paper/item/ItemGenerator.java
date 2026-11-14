package de.btegermany.core.paper.item;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

@RequiredArgsConstructor
public class ItemGenerator {
    private final NamespacedKey itemKey;

    public ItemStack customModel(String customModelData, Component customName) {
        return this.customModel(customModelData, customName, null);
    }

    public ItemStack customModel(String customModelData, Component customName, List<Component> lore) {
        return this.customModel(Material.PAPER, customModelData, customName, lore);
    }

    public ItemStack customModel(Material material, String customModelData, Component customName, List<Component> lore) {
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();
        if (customName != null) {
            meta.customName(customName);
        }
        if (lore != null) {
            meta.lore(lore);
        }

        CustomModelDataComponent modelDataComponent = meta.getCustomModelDataComponent();
        modelDataComponent.setStrings(List.of(customModelData));
        meta.setCustomModelDataComponent(modelDataComponent);

        // Add persistent marker to identify plugin items
        meta.getPersistentDataContainer().set(itemKey, PersistentDataType.BYTE, (byte) 1);

        item.setItemMeta(meta);
        return item;
    }

}