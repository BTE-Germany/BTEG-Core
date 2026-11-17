package de.btegermany.core.common.messaging;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

@RequiredArgsConstructor
public class Messenger<P extends Audience> {
    private final TranslationProvider<P, ?> translationProvider;

    public void send(@NonNull P player, String messageKey, TagResolver... placeholders) {
        this.send(player, this.translationProvider.get(player, messageKey, placeholders));
    }

    public void send(@NonNull P player, Component message) {
        player.sendMessage(message);
    }
}
