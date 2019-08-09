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
public class HypixelPlayer extends DynamicObjectNode {
    public HypixelPlayer(ObjectNode node) {
        super(node);
    }

    /**
     * Undashed UUID, 32 symbols.
     * Never null.
     *
     * @return Undashed UUID.
     */
    public String getUuid() {
        return get("uuid", null, JsonNode::asText);
    }

    /**
     * Display name, 1-16 symbols.
     * Maybe null for some players.
     *
     * @return Display name.
     */
    public String getDisplayname() {
        return get("displayname", null, JsonNode::asText);
    }

    /**
     * Custom prefix, colorized with minecraft color codes, contains space at the end.
     * Null if not present.
     *
     * @return Custom prefix.
     */
    public String getPrefix() {
        return get("prefix", null, JsonNode::asText);
    }

    /**
     * Rank.
     * Never null.
     *
     * @return Rank.
     */
    public Rank getRank() {
        String rank = get("rank", null, JsonNode::asText);
        String monthlyPackageRank = get("monthlyPackageRank", null, JsonNode::asText);
        String newPackageRank = get("newPackageRank", null, JsonNode::asText);
        String packageRank = get("packageRank", null, JsonNode::asText);

        String value;

        if (rank != null && !rank.equals("NORMAL")) {
            value = rank;
        } else if (monthlyPackageRank != null && !monthlyPackageRank.equals("NONE")) {
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
        return get("networkExp", 0L, JsonNode::asLong);
    }

    /**
     * Karma.
     *
     * @return Karma.
     */
    public long getKarma() {
        return get("karma", 0L, JsonNode::asLong);
    }

    /**
     * Achievement points.
     *
     * @return Achievement points.
     */
    public long getAchievementPoints() {
        return get("achievementPoints", 0L, JsonNode::asLong);
    }

    /**
     * First login on the network.
     * Maybe 0 for some players.
     *
     * @return First login on the network.
     */
    public long getFirstLogin() {
        return get("firstLogin", 0L, JsonNode::asLong);
    }

    /**
     * Last login on the network.
     * Always 0 for staff members, except for YT.
     *
     * @return Last login on the network.
     */
    public long getLastLogin() {
        return get("lastLogin", 0L, JsonNode::asLong);
    }

    /**
     * Last logout from the network.
     * Always 0 for staff members, except for YT.
     *
     * @return Last logout from the network.
     */
    public long getLastLogout() {
        return get("lastLogout", 0L, JsonNode::asLong);
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
        return getList("knownAliases", Collections.emptyList(), JsonNode::asText);
    }

    /**
     * MVP+ plus color.
     * Example: RED, BLACK.
     *
     * @return MVP+ plus color.
     */
    public String getRankPlusColor() {
        return get("rankPlusColor", "RED", JsonNode::asText);
    }

    /**
     * MVP++ rank color.
     * Possible values: GOLD, AQUA.
     *
     * @return MVP++ rank color.
     */
    public String getMonthlyRankColor() {
        return get("monthlyRankColor", "GOLD", JsonNode::asText);
    }

    /**
     * Social media.
     * Null if not present.
     *
     * @return Social media.
     */
    public SocialMedia getSocialMedia() {
        return get("socialMedia", null, node -> new SocialMedia((ObjectNode) node));
    }

    /**
     * Vanity meta.
     * Null if not present.
     *
     * @return Vanity meta.
     */
    public VanityMeta getVanityMeta() {
        return get("vainityMeta", null, node -> new VanityMeta((ObjectNode) node));
    }

    /**
     * Stats.
     * Null if not present.
     *
     * @return Stats.
     */
    public Stats getStats() {
        return get("stats", null, node -> new Stats((ObjectNode) node));
    }

    /**
     * Returns whether the player is a staff member
     * or a youtuber.
     *
     * @return Is player a staff member.
     */
    public boolean isStaff() {
        return !get("rank", "NORMAL", JsonNode::asText).equals("NORMAL");
    }

    /**
     * Returns whether the player is currently
     * online on the network.
     * Always false for staff members, except for YT.
     *
     * @return Is player currently online on the network.
     */
    public boolean isOnline() {
        long lastLogin = getLastLogin();
        long lastLogout = getLastLogout();

        return lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout;
    }

    /**
     * Retrieve this player's guild information.
     *
     * @param api Hypixel API.
     * @return Completable future of Guild.
     */
    public CompletableFuture<Guild> retrieveGuild(HypixelAPI api) {
        return api.retrieveGuildByPlayer(getUuid());
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
    public CompletableFuture<Session> retrieveSession(HypixelAPI api) {
        return api.retrieveSessionByUuid(getUuid());
    }
}
