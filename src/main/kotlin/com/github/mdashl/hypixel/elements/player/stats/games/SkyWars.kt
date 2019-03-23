package com.github.mdashl.hypixel.elements.player.stats.games

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.mdashl.hypixel.enums.*

class SkyWars(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val packages: List<String>
        get() = get("packages").map(JsonNode::textValue)

    val rankedKitsTimePlayed: Map<RankedKit, Long>
        get() = RankedKit.values()
            .map { it to getKitTimePlayed(it) }
            .sortedByDescending { it.second }
            .toMap()
            .filterValues { it != 0L }

    val activeRankedKit: RankedKit
        get() = RankedKit[get("activeKit_RANKED")?.textValue()] ?: RankedKit.DEFAULT

    val mostUsedRankedKit: RankedKit
        get() = rankedKitsTimePlayed.keys.firstOrNull() ?: RankedKit.DEFAULT

    val rankedRewards: Map<RankedDivision, List<RankedReward>>
        get() = RankedReward.values()
            .filter { it.apiName in packages }
            .groupBy { it.division }

    fun hasRewards(division: RankedDivision): Boolean = division.rewards.any { it.apiName in packages }

    fun getRating(season: RankedSeason): Int = get("SkyWars_skywars_rating_${season.date}_rating")?.intValue() ?: 0

    fun getPosition(season: RankedSeason): Int = get("SkyWars_skywars_rating_${season.date}_position")?.intValue() ?: 0

    fun getActiveCosmetic(cosmetic: CosmeticType): String? =
        get("active_${cosmetic.apiName}")?.textValue()
            ?.replace(cosmetic.apiName, "")
            ?.replace("_", " ")
            ?.replace("-", " ")
            ?.split(" ")
            ?.joinToString(" ") { it.capitalize() }
            ?.trim()

    fun getKitTimePlayed(kit: RankedKit): Long = get("time_played_${kit.apiName}")?.longValue() ?: 0

    fun getKills(mode: SkyWarsMode): Long = get("kills_${mode.apiName}")?.longValue() ?: 0

    fun getDeaths(mode: SkyWarsMode): Long = get("deaths_${mode.apiName}")?.longValue() ?: 0

    fun getWins(mode: SkyWarsMode): Long = get("wins_${mode.apiName}")?.longValue() ?: 0

    fun getLosses(mode: SkyWarsMode): Long = get("losses_${mode.apiName}")?.longValue() ?: 0

    fun getTimePlayed(mode: SkyWarsMode): Long = get("time_played_${mode.apiName}")?.longValue() ?: 0
}
