package de.btegermany.core.paper.setup;

import de.btegermany.core.common.setup.UtilsSetup;
import de.btegermany.core.paper.gui.GuiItems;
import de.btegermany.core.paper.gui.GuiUtils;
import de.btegermany.core.paper.item.ItemGenerator;
import org.bukkit.NamespacedKey;

public class PaperUtilsSetup extends UtilsSetup {
    public PaperUtilsSetup() {
        register(new GuiUtils());
    }

    public PaperUtilsSetup useItem(NamespacedKey itemKey) {
        ItemGenerator itemGenerator = new ItemGenerator(itemKey);
        register(itemGenerator, new GuiItems(itemGenerator));
        return this;
    }
}
