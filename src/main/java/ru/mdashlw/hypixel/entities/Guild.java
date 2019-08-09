package ru.mdashlw.hypixel.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mdashlw.hypixel.util.GuildLevelingUtil;

import java.util.List;
import java.util.Map;

/**
 * Guild on the network.
 */
// TODO Add missing documentation.
public class Guild {
    private String _id;
    private int coins;
    private int coinsEver;
    private long created;
    private List<Member> members;
    private String name;
    private boolean joinable;
    private String tag;
    private Map<Achievement, Integer> achievements;
    private long exp;
    private int legacyRanking;
    private String tagColor;
    private List<Rank> ranks;
    private String description;
    private List<GameType> preferredGames;
    private boolean publiclyListed;
    private int chatMute;
    @JsonProperty("name_lower")
    private String nameLower;
    private Map<GameType, Integer> guildExpByGameType;

    /**
     * Object id.
     *
     * @return Object id.
     */
    public String getId() {
        return _id;
    }

    /**
     * Coins.
     *
     * @return Coins.
     */
    public int getCoins() {
        return coins;
    }

    public int getCoinsEver() {
        return coinsEver;
    }

    /**
     * Time created.
     *
     * @return Time created.
     */
    public long getCreated() {
        return created;
    }

    /**
     * Members.
     *
     * @return Members.
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * Name.
     *
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether this guild is joinable
     * via /guild join.
     *
     * @return Is this guild joinable.
     */
    public boolean isJoinable() {
        return joinable;
    }

    /**
     * Custom tag.
     *
     * @return Custom tag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Obtained achievements stats.
     *
     * @return Obtained achievements stats.
     */
    public Map<Achievement, Integer> getAchievements() {
        return achievements;
    }

    /**
     * Experience.
     *
     * @return Experience.
     * @see GuildLevelingUtil
     */
    public long getExp() {
        return exp;
    }

    /**
     * Rank in the legacy leaderboard.
     *
     * @return Rank in the legacy leaderboard.
     */
    public int getLegacyRanking() {
        return legacyRanking;
    }

    /**
     * Tag color.
     * Example: YELLOW, GREEN.
     *
     * @return Tag color.
     */
    public String getTagColor() {
        return tagColor;
    }

    /**
     * Ranks.
     *
     * @return Ranks.
     */
    public List<Rank> getRanks() {
        return ranks;
    }

    /**
     * Description.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Preferred games.
     *
     * @return Preferred games.
     */
    public List<GameType> getPreferredGames() {
        return preferredGames;
    }

    /**
     * Returns whether this guild is publicly listed
     * in the menu.
     *
     * @return Is this guild publicly listed.
     */
    public boolean isPubliclyListed() {
        return publiclyListed;
    }

    public int getChatMute() {
        return chatMute;
    }

    /**
     * Lower case name.
     *
     * @return Lower case name.
     */
    public String getNameLower() {
        return nameLower;
    }

    public Map<GameType, Integer> getGuildExpByGameType() {
        return guildExpByGameType;
    }

    /**
     * Guild achievement.
     */
    public enum Achievement {
        WINNERS("Winners"),
        EXPERIENCE_KINGS("Experience Kings"),
        ONLINE_PLAYERS("Online Players");

        private String localizedName;

        Achievement(String localizedName) {
            this.localizedName = localizedName;
        }

        /**
         * Localized name.
         *
         * @return Localized name.
         */
        public String getLocalizedName() {
            return localizedName;
        }
    }

    /**
     * Guild member.
     */
    public static class Member {
        private String uuid;
        private String rank;
        private long joined;
        private int questParticipation;

        /**
         * Undashed UUID.
         *
         * @return Undashed UUID.
         */
        public String getUuid() {
            return uuid;
        }

        /**
         * Rank in the guild.
         *
         * @return Rank in the guild.
         */
        public String getRank() {
            return rank;
        }

        /**
         * Time joined.
         *
         * @return Time joined.
         */
        public long getJoined() {
            return joined;
        }

        public int getQuestParticipation() {
            return questParticipation;
        }
    }

    /**
     * Guild rank.
     */
    public static class Rank {
        private String name;
        @JsonProperty("default")
        private boolean _default;
        private String tag;
        private long created;
        private int priority;

        /**
         * Rank name.
         *
         * @return Rank name.
         */
        public String getName() {
            return name;
        }

        public boolean isDefault() {
            return _default;
        }

        public String getTag() {
            return tag;
        }

        /**
         * Time created.
         *
         * @return Time created.
         */
        public long getCreated() {
            return created;
        }

        public int getPriority() {
            return priority;
        }
    }
}
