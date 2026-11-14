package de.btegermany.core.common.messaging;

/**
 * Provides translations by a player's language
 * @param <P> Player type
 */
public interface TranslationProvider<P> {

    String get(P player, String key);

}
