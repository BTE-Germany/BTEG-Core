package de.btegermany.core.paper.item.head;

import org.bukkit.inventory.ItemStack;

public interface CustomHead {

    String getTexture();

    default ItemStack toItemStack() {
        return CustomHeadConverter.toItemStack(this);
    }

}
