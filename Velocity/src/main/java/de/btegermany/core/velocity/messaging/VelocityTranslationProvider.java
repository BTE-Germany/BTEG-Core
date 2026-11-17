package de.btegermany.core.velocity.messaging;

import com.velocitypowered.api.proxy.Player;
import de.btegermany.core.common.messaging.TranslationProvider;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class VelocityTranslationProvider extends TranslationProvider<Player, VelocityTranslationContext> {

    public VelocityTranslationProvider(MiniMessage miniMessage) {
        super(miniMessage);
    }

    @Override
    public String getRaw(Player player, String key) {
        //TODO: velocity message translation
        throw new RuntimeException("translation not implemented");
    }

    @Override
    public VelocityTranslationContext createContext(Player player) {
        return new VelocityTranslationContext(player, this);
    }
}
