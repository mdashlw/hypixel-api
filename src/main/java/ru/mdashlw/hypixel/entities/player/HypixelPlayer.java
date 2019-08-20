package ru.mdashlw.hypixel.entities.player;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.HypixelAPI;
import ru.mdashlw.hypixel.entities.Guild;
import ru.mdashlw.hypixel.entities.Rank;
import ru.mdashlw.hypixel.entities.Session;
import ru.mdashlw.hypixel.util.DynamicObjectNode;
import ru.mdashlw.hypixel.util.LevelingUtil;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Hypixel player.
 */
public final class HypixelPlayer extends DynamicObjectNode {
    public HypixelPlayer(final ObjectNode node) {
        super(node);
    }

    /**
     * Undashed UUID, 32 symbols.
     * Never null.
     *
     * @return Undashed UUID.
     */
    public String getUuid() {
        return this.get("uuid", null, JsonNode::asText);
    }

    /**
     * Display name, 1-16 symbols.
     * Maybe null for some players.
     *
     * @return Display name.
     */
    public String getDisplayname() {
        return this.get("displayname", null, JsonNode::asText);
    }

    /**
     * Custom prefix, colorized with minecraft color codes, contains space at the end.
     * Null if not present.
     *
     * @return Custom prefix.
     */
    public String getPrefix() {
        return this.get("prefix", null, JsonNode::asText);
    }

    /**
     * Rank.
     * Never null.
     *
     * @return Rank.
     */
    public Rank getRank() {
        final String rank = this.get("rank", null, JsonNode::asText);
        final String monthlyPackageRank = this.get("monthlyPackageRank", null, JsonNode::asText);
        final String newPackageRank = this.get("newPackageRank", null, JsonNode::asText);
        final String packageRank = this.get("packageRank", null, JsonNode::asText);

        final String value;

        if (rank != null && !"NORMAL".equals(rank)) {
            value = rank;
        } else if (monthlyPackageRank != null && !"NONE".equals(monthlyPackageRank)) {
            value = monthlyPackageRank;
        } else if (newPackageRank != null) {
            value = newPackageRank;
        } else if (packageRank != null) {
            value = packageRank;
        } else {
            return Rank.NORMAL;
        }

        return Rank.valueOf(value);
    }

    /**
     * Network experience.
     *
     * @return Network experience.
     * @see LevelingUtil
     */
    public long getNetworkExp() {
        return this.get("networkExp", 0L, JsonNode::asLong);
    }

    /**
     * Karma.
     *
     * @return Karma.
     */
    public long getKarma() {
        return this.get("karma", 0L, JsonNode::asLong);
    }

    /**
     * Achievement points.
     *
     * @return Achievement points.
     */
    public long getAchievementPoints() {
        return this.get("achievementPoints", 0L, JsonNode::asLong);
    }

    /**
     * First login on the network.
     * Maybe 0 for some players.
     *
     * @return First login on the network.
     */
    public long getFirstLogin() {
        return this.get("firstLogin", 0L, JsonNode::asLong);
    }

    /**
     * Last login on the network.
     * Always 0 for staff members, except for YT.
     *
     * @return Last login on the network.
     */
    public long getLastLogin() {
        return this.get("lastLogin", 0L, JsonNode::asLong);
    }

    /**
     * Last logout from the network.
     * Always 0 for staff members, except for YT.
     *
     * @return Last logout from the network.
     */
    public long getLastLogout() {
        return this.get("lastLogout", 0L, JsonNode::asLong);
    }

    /**
     * Known aliases.
     * first name - the name player had on first login,
     * last name - the name player had on last login.
     * Warning: duplicates are stripped.
     * Never null, never empty.
     *
     * @return Known aliases.
     */
    public List<String> getKnownAliases() {
        return this.getList("knownAliases", Collections.emptyList(), JsonNode::asText);
    }

    /**
     * MVP+ plus color.
     * Example: RED, BLACK.
     *
     * @return MVP+ plus color.
     */
    public String getRankPlusColor() {
        return this.get("rankPlusColor", "RED", JsonNode::asText);
    }

    /**
     * MVP++ rank color.
     * Possible values: GOLD, AQUA.
     *
     * @return MVP++ rank color.
     */
    public String getMonthlyRankColor() {
        return this.get("monthlyRankColor", "GOLD", JsonNode::asText);
    }

    /**
     * Social media.
     * Null if not present.
     *
     * @return Social media.
     */
    public SocialMedia getSocialMedia() {
        return this.get("socialMedia", null, node -> new SocialMedia((ObjectNode) node));
    }

    /**
     * Vanity meta.
     * Null if not present.
     *
     * @return Vanity meta.
     */
    public VanityMeta getVanityMeta() {
        return this.get("vainityMeta", null, node -> new VanityMeta((ObjectNode) node));
    }

    /**
     * Stats.
     * Null if not present.
     *
     * @return Stats.
     */
    public Stats getStats() {
        return this.get("stats", null, node -> new Stats((ObjectNode) node));
    }

    /**
     * Returns whether the player is a staff member
     * or a youtuber.
     *
     * @return Is player a staff member.
     */
    public boolean isStaff() {
        return !"NORMAL".equals(this.get("rank", "NORMAL", JsonNode::asText));
    }

    /**
     * Returns whether the player is currently
     * online on the network.
     * Always false for staff members, except for YT.
     *
     * @return Is player currently online on the network.
     */
    public boolean isOnline() {
        final long lastLogin = this.getLastLogin();
        final long lastLogout = this.getLastLogout();

        return lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout;
    }

    /**
     * Retrieve this player's guild information.
     *
     * @param api Hypixel API.
     * @return Completable future of Guild.
     */
    public CompletableFuture<Guild> retrieveGuild(final HypixelAPI api) {
        return api.retrieveGuildByPlayer(this.getUuid());
    }

    /**
     * Retrieve this player's session information.
     * Null if:
     * 1) player is offline.
     * 2) player is in lobby/housing.
     * 3) player is a staff member.
     *
     * @param api Hypixel API.
     * @return Completable future of Session.
     */
    public CompletableFuture<Session> retrieveSession(final HypixelAPI api) {
        return api.retrieveSessionByUuid(this.getUuid());
    }
}
