package ru.mdashlw.hypixel.entities.player.stats;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.util.DynamicObjectNode;

/**
 * Stats on UHC Champions.
 */
public final class UHC extends DynamicObjectNode {
    public UHC(final ObjectNode node) {
        super(node);
    }

    /**
     * Score.
     *
     * @return Score.
     */
    public int getScore() {
        return this.get("score", 0, JsonNode::asInt);
    }

    /**
     * Title.
     *
     * @return Title.
     */
    public Title getTitle() {
        return Title.getByScore(this.getScore());
    }

    /**
     * Wins on Solo mode.
     *
     * @return Wins on Solo mode.
     */
    public int getSoloWins() {
        return this.get("wins_solo", 0, JsonNode::asInt);
    }

    /**
     * Kills on Solo mode.
     *
     * @return Kills on Solo mode.
     */
    public int getSoloKills() {
        return this.get("kills_solo", 0, JsonNode::asInt);
    }

    /**
     * Deaths on Solo mode.
     *
     * @return Deaths on Solo mode.
     */
    public int getSoloDeaths() {
        return this.get("deaths_solo", 0, JsonNode::asInt);
    }

    /**
     * Heads eaten on Solo mode.
     *
     * @return Heads eaten on Solo mode.
     */
    public int getSoloHeadsEaten() {
        return this.get("heads_eaten_solo", 0, JsonNode::asInt);
    }

    /**
     * Wins on Team mode.
     *
     * @return Wins on Team mode.
     */
    public int getTeamWins() {
        return this.get("wins", 0, JsonNode::asInt);
    }

    /**
     * Kills on Team mode.
     *
     * @return Kills on Team mode.
     */
    public int getTeamKills() {
        return this.get("kills", 0, JsonNode::asInt);
    }

    /**
     * Deaths on Team mode.
     *
     * @return Deaths on Team mode.
     */
    public int getTeamDeaths() {
        return this.get("deaths", 0, JsonNode::asInt);
    }

    /**
     * Heads eaten on Team mode.
     *
     * @return Heads eaten on Team mode.
     */
    public int getTeamHeadsEaten() {
        return this.get("heads_eaten", 0, JsonNode::asInt);
    }

    /**
     * Currently equipped kit.
     *
     * @return Currently equipped kit or null.
     */
    public Kit getEquippedKit() {
        return this.get("equippedKit", null, node -> Kit.valueOf(node.asText()));
    }

    /**
     * Title on UHC.
     */
    public enum Title {
        RECRUIT(1, 0, "Recruit"),
        INITIATE(2, 10, "Initiate"),
        SOLDIER(3, 60, "Soldier"),
        SERGEANT(4, 210, "Sergeant"),
        KNIGHT(5, 460, "Knight"),
        CAPTAIN(6, 960, "Captain"),
        CENTURION(7, 1710, "Centurion"),
        GLADIATOR(8, 2710, "Gladiator"),
        WARLORD(9, 5210, "Warlord"),
        CHAMPION(10, 10210, "Champion"),
        CHAMPION_PLUS(11, 13210, "Champion"),
        CHAMPION_PLUS_PLUS(12, 16210, "Champion"),
        CHAMPION_PLUS_PLUS_PLUS(13, 19210, "Champion"),
        CHAMPION_PLUS_PLUS_PLUS_PLUS(14, 22210, "Champion"),
        HIGH_CHAMPION(15, 25210, "High Champion");

        private final int star;
        private final int scoreNeeded;
        private final String localizedName;

        Title(final int star, final int scoreNeeded, final String localizedName) {
            this.star = star;
            this.scoreNeeded = scoreNeeded;
            this.localizedName = localizedName;
        }

        /**
         * Get title by score.
         *
         * @param score Score, must be positive.
         * @return Title you get with score of {@code score}.
         */
        public static Title getByScore(final int score) {
            if (score < 0) {
                throw new IllegalStateException("Score must be positive.");
            }

            final Title[] values = values();

            for (int i = values.length - 1; i >= 0; i--) {
                final Title title = values[i];

                if (title.scoreNeeded <= score) {
                    return title;
                }
            }

            return null;
        }

        /**
         * 1-indexed star.
         *
         * @return 1-indexed star.
         */
        public int getStar() {
            return this.star;
        }

        /**
         * Minimum score needed to gain this title.
         *
         * @return Minimum score needed to gain this title.
         */
        public int getScoreNeeded() {
            return this.scoreNeeded;
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
     * UHC Kit.
     */
    public enum Kit {
        WORKING_TOOLS("Working Tools"),
        ARCHERY_TOOLS("Archery Tools"),
        ECOLOGIST("Ecologist"),
        LOOTER("Looter"),
        MAGIC_TOOLS("Magic Tools"),
        HORSEMAN("Horseman"),
        FARMER("Farmer"),
        TRAPPER("Trapper"),
        LUNCH_BOX("Lunch Box"),
        LEATHER_ARMOR("Leather Armor");

        private final String localizedName;

        Kit(final String localizedName) {
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
}
