package de.btegermany.core.common.setup;

import java.util.function.Consumer;

public class PluginSetup implements Disableable {
    private final DisableablesManager disableablesManager = new DisableablesManager();

    protected <T> void executeStep(Consumer<T> consumer, T step) {
        this.disableablesManager.storeIfDisableable(step);
        consumer.accept(step);
    }

    @Override
    public void onDisable() {
        this.disableablesManager.executeAll();
    }

}
