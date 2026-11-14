package de.btegermany.core.velocity.messaging;

import com.velocitypowered.api.proxy.Player;
import de.btegermany.core.common.messaging.Messenger;
import de.btegermany.core.common.messaging.TranslationProvider;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jspecify.annotations.NonNull;

public class VelocityMessenger extends Messenger<Player> {

    public VelocityMessenger(MiniMessage miniMessage, TranslationProvider<Player> translationProvider) {
        super(miniMessage, translationProvider);
    }

}
