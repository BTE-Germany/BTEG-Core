package de.btegermany.core.common.messaging;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

@RequiredArgsConstructor
public class TranslationContext<P> {
    private final P player;
    private final TranslationProvider<P, ?> translationProvider;

    public Component get(String messageKey, TagResolver... placeholders) {
        return this.translationProvider.get(this.player, messageKey, placeholders);
    }

    public String getText(String messageKey, TagResolver... placeholders) {
        return this.translationProvider.getText(this.player, messageKey, placeholders);
    }
}
