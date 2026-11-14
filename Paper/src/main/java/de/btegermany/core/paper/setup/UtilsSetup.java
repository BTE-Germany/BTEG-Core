package de.btegermany.core.paper.setup;

import de.btegermany.core.paper.Utils;
import de.btegermany.core.paper.item.ItemGenerator;
import org.bukkit.NamespacedKey;

public class UtilsSetup {

    private ItemGenerator itemGenerator = null;

    public UtilsSetup item(NamespacedKey itemKey) {
        this.itemGenerator = new ItemGenerator(itemKey);
        return this;
    }

    Utils buildUtils() {
        return new Utils(this.itemGenerator);
    }

}
