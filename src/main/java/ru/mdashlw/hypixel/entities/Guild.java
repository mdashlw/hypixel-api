package ru.mdashlw.hypixel.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mdashlw.hypixel.util.GuildLevelingUtil;

import java.util.List;
import java.util.Map;

/**
 * Guild on the network.
 */
// TODO Add missing documentation.
public final class Guild {
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
     * Mongo object id.
     *
     * @return Mongo object id.
     */
    public String getId() {
        return this._id;
    }

    /**
     * Coins.
     *
     * @return Coins.
     */
    public int getCoins() {
        return this.coins;
    }

    public int getCoinsEver() {
        return this.coinsEver;
    }

    /**
     * Time created.
     *
     * @return Time created.
     */
    public long getCreated() {
        return this.created;
    }

    /**
     * Members.
     *
     * @return Members.
     */
    public List<Member> getMembers() {
        return this.members;
    }

    /**
     * Name.
     *
     * @return Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether this guild is joinable
     * via /guild join.
     *
     * @return Is this guild joinable.
     */
    public boolean isJoinable() {
        return this.joinable;
    }

    /**
     * Custom tag.
     *
     * @return Custom tag.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Obtained achievements stats.
     *
     * @return Obtained achievements stats.
     */
    public Map<Achievement, Integer> getAchievements() {
        return this.achievements;
    }

    /**
     * Experience.
     *
     * @return Experience.
     * @see GuildLevelingUtil
     */
    public long getExp() {
        return this.exp;
    }

    /**
     * Rank in the legacy leaderboard.
     *
     * @return Rank in the legacy leaderboard.
     */
    public int getLegacyRanking() {
        return this.legacyRanking;
    }

    /**
     * Tag color.
     * Example: YELLOW, GREEN.
     *
     * @return Tag color.
     */
    public String getTagColor() {
        return this.tagColor;
    }

    /**
     * Ranks.
     *
     * @return Ranks.
     */
    public List<Rank> getRanks() {
        return this.ranks;
    }

    /**
     * Description.
     *
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Preferred games.
     *
     * @return Preferred games.
     */
    public List<GameType> getPreferredGames() {
        return this.preferredGames;
    }

    /**
     * Returns whether this guild is publicly listed
     * in the menu.
     *
     * @return Is this guild publicly listed.
     */
    public boolean isPubliclyListed() {
        return this.publiclyListed;
    }

    public int getChatMute() {
        return this.chatMute;
    }

    /**
     * Lower case name.
     *
     * @return Lower case name.
     */
    public String getNameLower() {
        return this.nameLower;
    }

    public Map<GameType, Integer> getGuildExpByGameType() {
        return this.guildExpByGameType;
    }

    /**
     * Guild achievement.
     */
    public enum Achievement {
        WINNERS("Winners"),
        EXPERIENCE_KINGS("Experience Kings"),
        ONLINE_PLAYERS("Online Players");

        private final String localizedName;

        Achievement(final String localizedName) {
            this.localizedName = localizedName;
        }

        /**
         * Localized name.
         *
         * @return Localized name.
         */
        public String getLocalizedName() {
            return this.localizedName;
        }
    }

    /**
     * Guild member.
     */
    public static final class Member {
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
            return this.uuid;
        }

        /**
         * Rank in the guild.
         *
         * @return Rank in the guild.
         */
        public String getRank() {
            return this.rank;
        }

        /**
         * Time joined.
         *
         * @return Time joined.
         */
        public long getJoined() {
            return this.joined;
        }

        public int getQuestParticipation() {
            return this.questParticipation;
        }
    }

    /**
     * Guild rank.
     */
    public static final class Rank {
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
            return this.name;
        }

        public boolean isDefault() {
            return this._default;
        }

        public String getTag() {
            return this.tag;
        }

        /**
         * Time created.
         *
         * @return Time created.
         */
        public long getCreated() {
            return this.created;
        }

        public int getPriority() {
            return this.priority;
        }
    }
}
