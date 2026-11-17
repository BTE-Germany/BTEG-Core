package de.btegermany.core.velocity.setup;

import de.btegermany.core.common.setup.UtilsSetup;
import de.btegermany.core.velocity.utils.ConnectionUtils;

public class VelocityUtilsSetup extends UtilsSetup {
    public VelocityUtilsSetup(VelocityPlugin plugin) {
        register(new ConnectionUtils(plugin.getMessenger()));
    }
}
