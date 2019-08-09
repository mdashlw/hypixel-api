package ru.mdashlw.hypixel.entities.player;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.util.DynamicObjectNode;

import java.util.Collections;
import java.util.Map;

/**
 * Social media for a player.
 */
public class SocialMedia extends DynamicObjectNode {
    SocialMedia(ObjectNode node) {
        super(node);
    }

    /**
     * Social media links.
     *
     * @return Social media links.
     */
    public Map<Type, String> getLinks() {
        return getMap("links", Collections.emptyMap(), Type::valueOf, JsonNode::asText);
    }

    public enum Type {
        /**
         * Hypixel Forums
         * Link: hypixel.net
         */
        HYPIXEL("Hypixel Forums"),
        /**
         * YouTube.
         * Link: youtube.com
         */
        YOUTUBE("YouTube"),
        /**
         * Twitter.
         * Link: twitter.com
         */
        TWITTER("Twitter"),
        /**
         * Twitch.
         * Link: twitch.tv
         */
        TWITCH("Twitch"),
        /**
         * Mixer.
         * Link: mixer.com
         */
        MIXER("Mixer"),
        /**
         * Instagram.
         * Link: instagram.com
         */
        INSTAGRAM("Instagram"),
        /**
         * Discord.
         * No link.
         */
        DISCORD("Discord", false);

        private String localizedName;
        private boolean link;

        Type(String localizedName) {
            this(localizedName, true);
        }

        Type(String localizedName, boolean link) {
            this.localizedName = localizedName;
            this.link = link;
        }

        /**
         * Get localized name.
         *
         * @return Localized name.
         */
        public String getLocalizedName() {
            return localizedName;
        }

        /**
         * Returns whether this type value is supposed
         * to be a URL.
         *
         * @return Is this type should be linked
         * with a URL.
         */
        public boolean isLink() {
            return link;
        }
    }
}
