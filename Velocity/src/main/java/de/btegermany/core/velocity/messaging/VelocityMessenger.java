package de.btegermany.core.velocity.messaging;

import com.velocitypowered.api.proxy.Player;
import de.btegermany.core.common.messaging.Messenger;

public class VelocityMessenger extends Messenger<Player> {

    public VelocityMessenger(VelocityTranslationProvider translationProvider) {
        super(translationProvider);
    }

}
