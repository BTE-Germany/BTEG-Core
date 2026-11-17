package de.btegermany.core.velocity.utils;

import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.btegermany.core.velocity.messaging.VelocityMessenger;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class ConnectionUtils {
    private final VelocityMessenger messenger;

    public void connect(Player player, RegisteredServer server) {
        this.connect(
                player,
                server,
                //TODO: move messages to separate files/plugin -> translation + more maintainable/coherence
                () -> this.messenger.send(player, Component.text("Server %s is offline.".formatted(server.getServerInfo().getName()), NamedTextColor.RED))
        );
    }

    public void connect(Player player, RegisteredServer server, Runnable onError) {
        Runnable sendErrorMessage = () -> {
          if (onError == null) {
              return;
          }
          onError.run();
        };

        server.ping().orTimeout(3, TimeUnit.SECONDS)
                .exceptionally(_ -> {
                    sendErrorMessage.run();
                    return null;
                })
                .thenAccept(pingResult -> {
                    if (pingResult == null) {
                        sendErrorMessage.run();
                        return;
                    }

                    player.createConnectionRequest(server).connect()
                            .exceptionally(_ -> {
                                sendErrorMessage.run();
                                return null;
                            })
                            .thenAccept(result -> {
                                if (result.isSuccessful() || result.getStatus() == ConnectionRequestBuilder.Status.CONNECTION_CANCELLED) {
                                    return;
                                }
                                sendErrorMessage.run();
                            });
                });
    }

}
