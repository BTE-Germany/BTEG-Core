package de.btegermany.core.velocity.messaging;

import com.velocitypowered.api.proxy.Player;
import de.btegermany.core.common.messaging.TranslationContext;

public class VelocityTranslationContext extends TranslationContext<Player> {
    public VelocityTranslationContext(Player player, VelocityTranslationProvider translationProvider) {
        super(player, translationProvider);
    }
}
