package de.btegermany.core.velocity.messaging;

import com.velocitypowered.api.proxy.Player;
import de.btegermany.core.common.messaging.TranslationProvider;

public class VelocityTranslationProvider implements TranslationProvider<Player> {

    @Override
    public String get(Player player, String key) {
        //TODO: velocity message translation
        throw new RuntimeException("translation not implemented");
    }

}
