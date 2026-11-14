package de.btegermany.core.velocity.setup;

import de.btegermany.core.velocity.ConnectionUtils;
import de.btegermany.core.velocity.Utils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UtilsSetup {
    private final ConnectionUtils connection;

    public UtilsSetup(VelocityPlugin plugin) {
        this.connection = new ConnectionUtils(plugin.getMessenger());
    }

    Utils buildUtils() {
        return new Utils(this.connection);
    }

}
