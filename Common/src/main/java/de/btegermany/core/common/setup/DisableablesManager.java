package de.btegermany.core.common.setup;

import java.util.HashSet;
import java.util.Set;

public class DisableablesManager {
    private final Set<Disableable> disableables = new HashSet<>();

    public void storeIfDisableable(Object value) {
        if (value instanceof Disableable) {
            this.disableables.add((Disableable) value);
        }
    }

    public void executeAll() {
        this.disableables.forEach(Disableable::onDisable);
    }

}
