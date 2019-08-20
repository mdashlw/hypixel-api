package ru.mdashlw.hypixel.entities.player.stats;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.util.DynamicObjectNode;
import ru.mdashlw.hypixel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stats on SkyWars.
 */
public final class SkyWars extends DynamicObjectNode {
    public SkyWars(final ObjectNode node) {
        super(node);
    }

    /**
     * Packages.
     *
     * @return Packages.
     */
    // TODO Create a enum.
    public List<String> getPackages() {
        return this.getList("packages", Collections.emptyList(), JsonNode::asText);
    }

    /**
     * Experience.
     *
     * @return Experience.
     */
    public long getExperience() {
        return this.get("skywars_experience", 0L, JsonNode::asLong);
    }

    /**
     * Formatted level, colorized with minecraft color codes.
     * Maybe null for players that have not logged in
     * since the leveling update.
     *
     * @return Formatted level.
     */
    public String getFormattedLevel() {
        return this.get("levelFormatted", null, JsonNode::asText);
    }

    /**
     * Overall wins.
     *
     * @return Overall wins.
     */
    public int getWins() {
        return this.get("wins", 0, JsonNode::asInt);
    }

    /**
     * Overall losses.
     *
     * @return Overall losses.
     */
    public int getLosses() {
        return this.get("losses", 0, JsonNode::asInt);
    }

    /**
     * Overall kills.
     *
     * @return Overall kills.
     */
    public int getKills() {
        return this.get("kills", 0, JsonNode::asInt);
    }

    /**
     * Overall deaths.
     *
     * @return Overall deaths.
     */
    public int getDeaths() {
        return this.get("deaths", 0, JsonNode::asInt);
    }

    /**
     * Souls.
     *
     * @return Souls.
     */
    public int getSouls() {
        return this.get("souls", 0, JsonNode::asInt);
    }

    /**
     * Cosmetic tokens.
     *
     * @return Cosmetic tokens.
     */
    public int getCosmeticTokens() {
        return this.get("cosmetic_tokens", 0, JsonNode::asInt);
    }

    /**
     * Total time played.
     *
     * @return Total time played.
     */
    public long getTimePlayed() {
        return this.get("time_played", 0L, JsonNode::asLong);
    }

    /**
     * Wins on {@code mode}.
     *
     * @param mode Mode.
     * @return Wins on {@code mode}.
     */
    public int getWins(final Mode mode) {
        return this.get("wins_" + mode.apiName, 0, JsonNode::asInt);
    }

    /**
     * Losses on {@code mode}.
     *
     * @param mode Mode.
     * @return Losses on {@code mode}.
     */
    public int getLosses(final Mode mode) {
        return this.get("losses_" + mode.apiName, 0, JsonNode::asInt);
    }

    /**
     * Kills on {@code mode}.
     *
     * @param mode Mode.
     * @return Kills on {@code mode}.
     */
    public int getKills(final Mode mode) {
        return this.get("kills_" + mode.apiName, 0, JsonNode::asInt);
    }

    /**
     * Deaths on {@code mode}.
     *
     * @param mode Mode.
     * @return Deaths on {@code mode}.
     */
    public int getDeaths(final Mode mode) {
        return this.get("deaths_" + mode.apiName, 0, JsonNode::asInt);
    }

    /**
     * Total time played on {@code mode}.
     *
     * @param mode Mode.
     * @return Total time played on {@code mode}.
     */
    public long getTimePlayed(final Mode mode) {
        return this.get("time_played_" + mode.apiName, 0L, JsonNode::asLong);
    }

    /**
     * Currently equipped package for {@code type}.
     *
     * @param type Cosmetic type.
     * @return Currently equipped package for {@code type} or null.
     */
    public String getActiveCosmetic(final CosmeticType type) {
        return this.get("active_" + type.apiName, null, node -> {
            String value = node.asText();

            value = StringUtil.stripUnderscoresAndDashes(value);
            value = value.replaceFirst(type.apiName, "");
            value = StringUtil.capitalize(value);

            return value;
        });
    }

    /**
     * Currently active kit on {@code type}.
     *
     * @param type Type.
     * @param <T>  Kit. {@link NormalKit}, {@link InsaneKit}, {@link MegaKit}, {@link RankedKit}.
     * @return Currently active kit on {@code type} or null.
     */
    @SuppressWarnings("unchecked")
    public <T extends Kit> T getActiveKit(final Type type) {
        return this.get("activeKit_" + type.apiName, null, node -> {
            final String text = node.asText();

            for (final Kit kit : type.kits) {
                if (kit.getPackageName().equals(text)) {
                    return (T) kit;
                }
            }

            return null;
        });
    }

    /**
     * Returns whether the player has
     * any reward from {@code division}.
     *
     * @param division Ranked division.
     * @return Does player have any rewards from {@code division}.
     */
    public boolean hasRewards(final RankedDivision division) {
        final List<String> packages = this.getPackages();

        for (final RankedReward reward : division.getRewards()) {
            if (packages.contains(reward.packageName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Ranked rewards.
     *
     * @return Ranked rewards.
     */
    public List<RankedReward> getRankedRewards() {
        final List<String> packages = this.getPackages();
        final List<RankedReward> rewards = new ArrayList<>();

        for (final RankedReward reward : RankedReward.values()) {
            if (packages.contains(reward.packageName)) {
                rewards.add(reward);
            }
        }

        return rewards;
    }

    /**
     * Game type.
     */
    public enum Type {
        NORMAL("SOLO", "Normal", NormalKit.values()),
        INSANE("TEAMS", "Insane", InsaneKit.values()),
        MEGA("MEGA", "Mega", MegaKit.values()),
        RANKED("RANKED", "Ranked", RankedKit.values());

        private final String apiName;
        private final String localizedName;
        private final Kit[] kits;

        Type(final String apiName, final String localizedName, final Kit[] kits) {
            this.apiName = apiName;
            this.localizedName = localizedName;
            this.kits = kits;
        }

        /**
         * API name.
         *
         * @return API name.
         */
        public String getApiName() {
            return this.apiName;
        }

        /**
         * Localized name.
         *
         * @return Localized name.
         */
        public String getLocalizedName() {
            return this.localizedName;
        }

        /**
         * Available kits for game type.
         *
         * @return Available kits for game type.
         */
        public Kit[] getKits() {
            return this.kits;
        }
    }

    /**
     * Game mode.
     */
    public enum Mode {
        SOLO("solo", "Solo"),
        SOLO_NORMAL("solo_normal", "Solo Normal"),
        SOLO_INSANE("solo_insane", "Solo Insane"),
        DOUBLES("team", "Doubles"),
        DOUBLES_NORMAL("team_normal", "Doubles Normal"),
        DOUBLES_INSANE("team_insane", "Doubles Insane"),
        MEGA("mega", "Mega"),
        RANKED("ranked", "Ranked");

        private final String apiName;
        private final String localizedName;

        Mode(final String apiName, final String localizedName) {
            this.apiName = apiName;
            this.localizedName = localizedName;
        }

        /**
         * API name.
         *
         * @return API name.
         */
        public String getApiName() {
            return this.apiName;
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
     * Cosmetic type.
     */
    public enum CosmeticType {
        PROJECTILE_TRAIL("projectiletrail", "Projectile Trail"),
        CAGE("cage", "Cage"),
        VICTORY_DANCE("victorydance", "Victory Dance"),
        KILL_EFFECT("killeffect", "Kill Effect"),
        DEATH_CRY("deathcry", "Death Cry"),
        BALLOON("balloon", "Balloon"),
        KILL_MESSAGES("killmessages", "Kill Messages"),
        SPRAYS("sprays", "Sprays");

        private final String apiName;
        private final String localizedName;

        CosmeticType(final String apiName, final String localizedName) {
            this.apiName = apiName;
            this.localizedName = localizedName;
        }

        /**
         * API name.
         *
         * @return API name.
         */
        public String getApiName() {
            return this.apiName;
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
     * Kit on Normal mode.
     */
    public enum NormalKit implements Kit {
        DEFAULT("kit_basic_solo_default", "Default"),
        ARMORER("kit_advanced_solo_armorer", "Armorer"),
        ARMORSMITH("kit_basic_solo_armorsmith", "Armorsmith"),
        BASEBALL_PLAYER("kit_basic_solo_baseball-player", "Baseball Player"),
        CANNONEER("kit_advanced_solo_cannoneer", "Cannoneer"),
        ECOLOGIST("kit_basic_solo_ecologist", "Ecologist"),
        ENCHANTER("kit_advanced_solo_enchanter", "Enchanter"),
        ENDERCHEST("kit_enderchest_solo_enderchest", "Enderchest"),
        ENDERMAN("kit_advanced_solo_enderman", "Enderman"),
        FARMER("kit_advanced_solo_farmer", "Farmer"),
        FISHERMAN("kit_basic_solo_fisherman", "Fisherman"),
        HUNTER("kit_advanced_solo_hunter", "Hunter"),
        KNIGHT("kit_advanced_solo_knight", "Knight"),
        PHARAOH("kit_basic_solo_pharaoh", "Pharaoh"),
        PYRO("kit_advanced_solo_pyro", "Pyro"),
        ROOKIE("kit_basic_solo_rookie", "Rookie"),
        SNOWMAN("kit_basic_solo_snowman", "Snowman"),
        SPELEOLOGIST("kit_basic_solo_speleologist", "Speleologist"),
        TROLL("kit_basic_solo_troll", "Troll"),
        BATGUY("kit_basic_solo_batguy", "Batguy"),
        DISCO("kit_basic_solo_disco", "Disco"),
        ENERGIX("kit_basic_solo_energix", "Energix"),
        FROG("kit_basic_solo_frog", "Frog"),
        GRENADE("kit_basic_solo_grenade", "Grenade"),
        GUARDIAN("kit_advanced_solo_guardian", "Guardian"),
        HEALER("kit_basic_solo_healer", "Healer"),
        SCOUT("kit_basic_solo_scout", "Scout"),
        PRINCESS("kit_basic_solo_princess", "Princess"),
        ENGINEER("kit_advanced_solo_engineer", "Engineer"),
        SALMON("kit_advanced_solo_salmon", "Salmon"),
        PIG_RIDER("kit_advanced_solo_pig-rider", "Pig Rider"),
        SLIME("kit_advanced_solo_slime", "Slime"),
        JESTER("kit_advanced_solo_jester", "Jester"),
        ZOOKEEPER("kit_advanced_solo_zookeeper", "Zookeeper"),
        SLOTH("kit_advanced_solo_sloth", "Sloth"),
        MAGICIAN("kit_advanced_solo_magician", "Magician"),
        END_LORD("kit_mythical_end-lord", "End Lord"),
        MONSTER_TRAINER("kit_mythical_monster-trainer", "Monster Trainer"),
        NETHER_LORD("kit_mythical_nether-lord", "Nether Lord");

        private final String packageName;
        private final String localizedName;

        NormalKit(final String packageName, final String localizedName) {
            this.packageName = packageName;
            this.localizedName = localizedName;
        }

        @Override
        public String getPackageName() {
            return this.packageName;
        }

        @Override
        public String getLocalizedName() {
            return this.localizedName;
        }
    }

    /**
     * Kit on Insane mode.
     */
    public enum InsaneKit implements Kit {
        DEFAULT("kit_mining_team_default", "Default"),
        ARMORER("kit_defending_team_armorer", "Armorer"),
        ARMORSMITH("kit_supporting_team_armorsmith", "Armorsmith"),
        BASEBALL_PLAYER("kit_defending_team_baseball-player", "Baseball Player"),
        CANNONEER("kit_mining_team_cannoneer", "Cannoneer"),
        ECOLOGIST("kit_supporting_team_ecologist", "Ecologist"),
        ENCHANTER("kit_supporting_team_enchanter", "Enchanter"),
        ENDERMAN("kit_attacking_team_enderman", "Enderman"),
        GUARDIAN("kit_defending_team_guardian", "Guardian"),
        HEALER("kit_supporting_team_healer", "Healer"),
        HUNTER("kit_attacking_team_hunter", "Hunter"),
        KNIGHT("kit_attacking_team_knight", "Knight"),
        PHARAOH("kit_supporting_team_pharaoh", "Pharaoh"),
        ROOKIE("kit_supporting_team_rookie", "Pro"),
        SCOUT("kit_attacking_team_scout", "Scout"),
        SNOWMAN("kit_attacking_team_snowman", "Snowman"),
        SPELEOLOGIST("kit_mining_team_speleologist", "Speleologist"),
        BATGUY("kit_defending_team_batguy", "Batguy"),
        DISCO("kit_defending_team_disco", "Disco"),
        ENERGIX("kit_attacking_team_energix", "Energix"),
        FROG("kit_defending_team_frog", "Frog"),
        GRENADE("kit_attacking_team_grenade", "Grenade"),
        ENGINEER("kit_attacking_team_engineer", "Engineer"),
        PIG_RIDER("kit_attacking_team_pig-rider", "Pig Rider"),
        SALMON("kit_attacking_team_salmon", "Salmon"),
        SLIME("kit_attacking_team_slime", "Slime"),
        JESTER("kit_attacking_team_jester", "Jester"),
        ZOOKEEPER("kit_supporting_team_zookeeper", "Zookeeper"),
        SLOTH("kit_attacking_team_sloth", "Sloth"),
        MAGICIAN("kit_attacking_team_magician", "Magician"),
        ENDERCHEST("kit_enderchest_team_enderchest", "Enderchest"),
        FARMER("kit_defending_team_farmer", "Farmer"),
        FISHERMAN("kit_attacking_team_fisherman", "Fisherman"),
        PRINCESS("kit_supporting_team_princess", "Princess"),
        PYRO("kit_supporting_team_pyro", "Pyro"),
        TROLL("kit_supporting_team_troll", "Troll"),
        END_LORD("kit_mythical_end-lord", "End Lord"),
        MONSTER_TRAINER("kit_mythical_monster-trainer", "Monster Trainer"),
        NETHER_LORD("kit_mythical_nether-lord", "Nether Lord");

        private final String packageName;
        private final String localizedName;

        InsaneKit(final String packageName, final String localizedName) {
            this.packageName = packageName;
            this.localizedName = localizedName;
        }

        @Override
        public String getPackageName() {
            return this.packageName;
        }

        @Override
        public String getLocalizedName() {
            return this.localizedName;
        }
    }

    /**
     * Kit on Mega mode.
     */
    public enum MegaKit implements Kit {
        DEFAULT("kit_mega_mega_default", "Default"),
        ARMORER("kit_mega_mega_armorer", "Armorer"),
        ARMORSMITH("kit_mega_mega_armorsmith", "Armorsmith"),
        BASEBALL_PLAYER("kit_mega_mega_baseball-player", "Baseball Player"),
        CANNONEER("kit_mega_mega_cannoneer", "Cannoneer"),
        HEALER("kit_mega_mega_healer", "Healer"),
        HUNTER("kit_mega_mega_hunter", "Hunter"),
        KNIGHT("kit_mega_mega_knight", "Knight"),
        PALADIN("kit_mega_mega_paladin", "Paladin"),
        SCOUT("kit_mega_mega_scout", "Scout"),
        SKELETOR("kit_mega_mega_skeletor", "Skeletor"),
        WITCH("kit_mega_mega_witch", "Witch"),
        HELLBOUND("kit_mega_mega_hellhound", "Hellbound"),
        FISHERMAN("kit_mega_mega_fisherman", "Fisherman"),
        PYROMANIAC("kit_mega_mega_pyromaniac", "Pyromaniac");

        private final String packageName;
        private final String localizedName;

        MegaKit(final String packageName, final String localizedName) {
            this.packageName = packageName;
            this.localizedName = localizedName;
        }

        @Override
        public String getPackageName() {
            return this.packageName;
        }

        @Override
        public String getLocalizedName() {
            return this.localizedName;
        }
    }

    /**
     * Kit on Ranked mode.
     */
    public enum RankedKit implements Kit {
        DEFAULT("kit_ranked_ranked_default", "Default"),
        SCOUT("kit_ranked_ranked_scout", "Scout"),
        MAGICIAN("kit_ranked_ranked_magician", "Magician"),
        ARMORER("kit_ranked_ranked_armorer", "Armorer"),
        CHAMPION("kit_ranked_ranked_champion", "Champion"),
        BOWMAN("kit_ranked_ranked_bowman", "Bowman"),
        ATHLETE("kit_ranked_ranked_athlete", "Athlete"),
        BLACKSMITH("kit_blacksmith_ranked_blacksmith", "Blacksmith"),
        HEALER("kit_ranked_ranked_healer", "Healer"),
        PYROMANCER("kit_ranked_ranked_pyromancer", "Pyromancer"),
        HOUND("kit_ranked_ranked_hound", "Hound"),
        PALADIN("kit_ranked_ranked_paladin", "Paladin");

        private final String packageName;
        private final String localizedName;

        RankedKit(final String packageName, final String localizedName) {
            this.packageName = packageName;
            this.localizedName = localizedName;
        }

        @Override
        public String getPackageName() {
            return this.packageName;
        }

        @Override
        public String getLocalizedName() {
            return this.localizedName;
        }
    }

    /**
     * Division in Ranked mode.
     */
    public enum RankedDivision {
        MASTERS("Masters"),
        DIAMOND("Diamond"),
        GOLD("Gold"),
        IRON("Iron"),
        STONE("Stone"),
        WOOD("Wood");

        private final String localizedName;

        RankedDivision(final String localizedName) {
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

        /**
         * Rewards you can gain from this division.
         *
         * @return Rewards you can gain from this division.
         */
        public List<RankedReward> getRewards() {
            final List<RankedReward> rewards = new ArrayList<>();

            for (final RankedReward reward : RankedReward.values()) {
                if (reward.division == this) {
                    rewards.add(reward);
                }
            }

            return rewards;
        }
    }

    /**
     * Reward in Ranked mode.
     */
    public enum RankedReward {
        FINAL_SMASH(RankedDivision.MASTERS, "killeffect_final_smash", "Final Smash"),
        HEAD_ROCKET(RankedDivision.MASTERS, "killeffect_head_rocket", "Head Rocket"),
        DRAGON_RIDER(RankedDivision.MASTERS, "victorydance_dragon_rider", "Dragon Rider"),

        HEART_EXPLOSION(RankedDivision.DIAMOND, "killeffect_heart_explosion", "Heart Explosion"),
        GREEN_STAR(RankedDivision.DIAMOND, "projectiletrail_green_star", "Green Star"),
        NOTES(RankedDivision.DIAMOND, "projectiletrail_notes", "Notes"),
        BAZINGA(RankedDivision.DIAMOND, "deathcry_bazinga", "Bazinga"),
        GRUMPY_VILLAGER(RankedDivision.DIAMOND, "deathcry_grumpy_villager", "Grumpy Villager"),
        MONSTER_BURP(RankedDivision.DIAMOND, "deathcry_monster_burp", "Monster Burp"),
        SAD_PUPPY(RankedDivision.DIAMOND, "deathcry_sad_puppy", "Sad Puppy"),
        MAGIC_BOX(RankedDivision.DIAMOND, "cage_magic-box-cage", "Magic Box"),

        GUARDIANS(RankedDivision.GOLD, "victorydance_guardians", "Guardians"),
        BLOOD_EXPLOSION(RankedDivision.GOLD, "killeffect_blood_explosion", "Blood Explosion"),
        HEARTS(RankedDivision.GOLD, "projectiletrail_hearts", "Hearts");

        private final RankedDivision division;
        private final String packageName;
        private final String localizedName;

        RankedReward(final RankedDivision division, final String packageName, final String localizedName) {
            this.division = division;
            this.packageName = packageName;
            this.localizedName = localizedName;
        }

        /**
         * Division this ranked reward can be gained from.
         *
         * @return Division this ranked reward can be gained from.
         */
        public RankedDivision getDivision() {
            return this.division;
        }

        /**
         * Package name.
         *
         * @return Package name.
         */
        public String getPackageName() {
            return this.packageName;
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
     * Hat on Ranked mode.
     */
    public enum RankedHat {
        ENDER(RankedDivision.MASTERS, "hat_ender_steve"),
        DIAMOND(RankedDivision.DIAMOND, "hat_diamond_steve"),
        GOLD(RankedDivision.GOLD, "hat_gold_steve"),
        IRON(RankedDivision.IRON, "hat_iron_steve"),
        STONE(RankedDivision.STONE, "hat_stone_steve"),
        WOOD(RankedDivision.WOOD, "hat_wood_steve");

        private final RankedDivision division;
        private final String packageName;

        RankedHat(final RankedDivision division, final String packageName) {
            this.division = division;
            this.packageName = packageName;
        }

        /**
         * Division you can gain this hat form.
         *
         * @return Division you can gain this hat form.
         */
        public RankedDivision getDivision() {
            return this.division;
        }

        /**
         * Package name.
         *
         * @return Package name.
         */
        public String getPackageName() {
            return this.packageName;
        }
    }

    interface Kit extends ru.mdashlw.hypixel.entities.Kit {
    }
}
