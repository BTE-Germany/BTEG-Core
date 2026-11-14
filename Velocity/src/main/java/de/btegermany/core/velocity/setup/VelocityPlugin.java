package de.btegermany.core.velocity.setup;

import co.aikar.commands.VelocityCommandManager;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import de.btegermany.core.velocity.Utils;
import de.btegermany.core.velocity.messaging.VelocityMessenger;
import lombok.Getter;
import org.slf4j.Logger;

import java.nio.file.Path;

public class VelocityPlugin {
    @Getter
    private final ProxyServer proxyServer;
    @Getter
    private final Logger logger;
    @Getter
    private final Path dataDirectoryPath;
    protected final VelocityPluginSetup setup;

    @Inject
    public VelocityPlugin(ProxyServer proxyServer, Logger logger, @DataDirectory Path dataDirectoryPath) {
        this.proxyServer = proxyServer;
        this.logger = logger;
        this.dataDirectoryPath = dataDirectoryPath;
        this.setup = new VelocityPluginSetup(this);
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        this.setup.onDisable();
    }

    public VelocityCommandManager getCommandManager() {
        return this.setup.getCommandManager();
    }

    public VelocityMessenger getMessenger() {
        return this.setup.getMessenger();
    }

    public Utils getUtils() {
        return this.setup.getUtils();
    }
}
