package de.btegermany.core.common.setup;

import java.util.function.BiConsumer;

public class PluginSetup implements Disableable {
    private final DisableablesManager disableablesManager = new DisableablesManager();

    protected <T extends PluginSetup, U> void executeStep(BiConsumer<T, U> consumer, T setup, U step) {
        this.disableablesManager.storeIfDisableable(step);
        consumer.accept(setup, step);
    }

    @Override
    public void onDisable() {
        this.disableablesManager.executeAll();
    }

}
