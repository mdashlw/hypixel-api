package ru.mdashlw.hypixel.api.entities.player.stats.games

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.entities.player.stats.Game
import ru.mdashlw.hypixel.api.enums.*
import ru.mdashlw.hypixel.api.ranked.RankedSeason
import ru.mdashlw.hypixel.api.util.get
import ru.mdashlw.hypixel.api.util.int
import ru.mdashlw.hypixel.api.util.long
import ru.mdashlw.hypixel.api.util.text

class SkyWars(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children), Game {
    override val localizedName: String = "SkyWars"

    val packages: List<String>
        get() = get("packages", emptyList()) { it.map(JsonNode::textValue) }

    val rankedKitsTimePlayed: Map<RankedKit, Long>
        get() = RankedKit.values()
            .map { it to getKitTimePlayed(it) }
            .sortedByDescending { it.second }
            .toMap()
            .filterValues { it != 0L }

    val activeRankedKit: RankedKit
        get() = get("activeKit_RANKED", null, JsonNode::text)?.let(RankedKit.Companion::get) ?: RankedKit.DEFAULT

    val mostUsedRankedKit: RankedKit
        get() = rankedKitsTimePlayed.keys.firstOrNull() ?: RankedKit.DEFAULT

    val rankedRewards: Map<RankedDivision, List<RankedReward>>
        get() = RankedReward.values()
            .filter { it.apiName in packages }
            .groupBy { it.division }

    fun hasRewards(division: RankedDivision): Boolean = division.rewards.any { it.apiName in packages }

    fun getRating(season: RankedSeason): Int =
        get("SkyWars_skywars_rating_${season.hypixelDate}_rating", 0, JsonNode::int)

    fun getPosition(season: RankedSeason): Int =
        get("SkyWars_skywars_rating_${season.hypixelDate}_position", 0, JsonNode::int)

    fun getActiveCosmetic(cosmetic: CosmeticType): String? =
        get("active_${cosmetic.apiName}", null) {
            it.text()
                .replace(cosmetic.apiName, "")
                .replace("_", " ")
                .replace("-", " ")
                .split(" ")
                .joinToString(" ", transform = String::capitalize)
                .trim()
        }

    fun getKitTimePlayed(kit: RankedKit): Long = get("time_played_${kit.apiName}", 0, JsonNode::long)

    fun getKills(mode: SkyWarsMode): Long = get("kills_${mode.apiName}", 0, JsonNode::long)

    fun getDeaths(mode: SkyWarsMode): Long = get("deaths_${mode.apiName}", 0, JsonNode::long)

    fun getWins(mode: SkyWarsMode): Long = get("wins_${mode.apiName}", 0, JsonNode::long)

    fun getLosses(mode: SkyWarsMode): Long = get("losses_${mode.apiName}", 0, JsonNode::long)

    fun getTimePlayed(mode: SkyWarsMode): Long = get("time_played_${mode.apiName}", 0, JsonNode::long)
}
