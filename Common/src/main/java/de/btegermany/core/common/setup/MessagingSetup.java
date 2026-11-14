package de.btegermany.core.common.setup;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public class MessagingSetup {
    private final TagResolver.Builder tagResolverBuilder;

    public MessagingSetup() {
        this.tagResolverBuilder = TagResolver.builder()
                .resolver(StandardTags.defaults());
    }

    public MessagingSetup resolvers(TagResolver... resolvers) {
        this.tagResolverBuilder.resolvers(resolvers);
        return this;
    }

    public MessagingSetup prefix(String prefix) {
        this.resolvers(Placeholder.parsed("prefix", prefix));
        return this;
    }

    public MiniMessage buildMiniMessage() {
        return MiniMessage.builder()
                .tags(this.tagResolverBuilder.build())
                .build();
    }
}
