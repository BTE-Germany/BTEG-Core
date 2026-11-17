package de.btegermany.core.common.setup;

import de.btegermany.core.common.utils.CompareUtils;
import de.btegermany.core.common.utils.Utils;
import lombok.Getter;

public class UtilsSetup {
    @Getter
    private final Utils utils = new Utils();

    public UtilsSetup() {
        this.utils.register(new CompareUtils());
    }

    public UtilsSetup register(Object... utilsObjects) {
        for (Object utilsObject : utilsObjects) {
            this.utils.register(utilsObject);
        }
        return this;
    }

    public <T> T getRegistered(Class<T> utilsClass) {
        return utils.get(utilsClass);
    }
}
