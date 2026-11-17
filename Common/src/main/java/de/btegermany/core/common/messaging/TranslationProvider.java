package de.btegermany.core.common.messaging;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

/**
 * Provides translations by a player's language
 * @param <P> Player type
 */
@RequiredArgsConstructor
public abstract class TranslationProvider<P, C extends TranslationContext<P>> {
    private final MiniMessage miniMessage;

    public Component get(@NonNull P player, String messageKey, TagResolver... placeholders) {
        return this.miniMessage.deserialize(
                this.getRaw(player, messageKey),
                placeholders
        );
    }

    public String getText(@NonNull P player, String messageKey, TagResolver... placeholders) {
        return ((TextComponent) this.get(player, messageKey, placeholders)).content();
    }

    public abstract String getRaw(P player, String key);

    public abstract C createContext(P player);
}
