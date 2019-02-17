package com.github.mdashl.hypixel.elements.player.stats.games

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.mdashl.hypixel.enums.*

class SkyWars(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {

    val packages: List<String> by lazy { get("packages").map(JsonNode::textValue) }

    val rankedKitsUsage: Map<RankedKit, Long> by lazy {
        RankedKit.values()
            .map { it to getKitTimePlayed(it) }
            .sortedByDescending { it.second }
            .toMap()
            .filterValues { it != 0L }
    }

    val activeRankedKit: RankedKit by lazy { RankedKit[get("activeKit_RANKED")?.textValue()] ?: RankedKit.DEFAULT }

    val mostUsedRankedKit: RankedKit by lazy { rankedKitsUsage.keys.firstOrNull() ?: RankedKit.DEFAULT }

    val rankedSeasons: Map<RankedSeason, Pair<Int, Int>> by lazy {
        RankedSeason.values().slice(22..RankedSeason.CURRENT_SEASON.ordinal + 1)
            .map { season ->
                val rating = getRating(season)
                val position = getPosition(season)

                if (rating == 0 && position == 0) {
                    null to (0 to 0)
                }

                season to (rating to position + 1)
            }
            .toMap()
            .filterValues { (rating, position) -> rating != 0 && position != 0 }
    }

    val rankedRewards: Map<RankedDivision, List<RankedReward>> by lazy {
        RankedReward.values()
            .filter { it.apiName in packages }
            .groupBy { it.division }
    }

    fun hasRewards(division: RankedDivision): Boolean = division.rewards.any { it.apiName in packages }

    fun getRating(season: RankedSeason): Int = get("SkyWars_skywars_rating_${season.date}_rating")?.intValue() ?: 0

    fun getPosition(season: RankedSeason): Int = get("SkyWars_skywars_rating_${season.date}_position")?.intValue() ?: 0

    fun getActiveItem(item: String): String? =
        get("active_$item")?.textValue()
            ?.replace(item, "")
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
