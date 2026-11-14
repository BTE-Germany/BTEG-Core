package de.btegermany.core.paper.gui.itemdata;

import de.btegermany.core.paper.item.head.CustomHead;
import org.bukkit.inventory.ItemStack;

public interface ItemData {

    ItemStack getItemStack(CustomHead defaultHead);

}
