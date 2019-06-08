package ru.mdashlw.hypixel.api.entities.player.stats.games

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.entities.player.stats.Game
import ru.mdashlw.hypixel.api.enums.*
import ru.mdashlw.hypixel.api.extensions.uncolorize
import ru.mdashlw.hypixel.api.interfaces.kits.SkyWarsKit
import ru.mdashlw.hypixel.api.util.get
import ru.mdashlw.hypixel.api.util.int
import ru.mdashlw.hypixel.api.util.long
import ru.mdashlw.hypixel.api.util.text

class SkyWars(obj: ObjectNode) : Game(obj) {
    override val apiName: String = "SkyWars"
    override val localizedName: String = "SkyWars"

    val experience: Long
        get() = get("skywars_experience", 0, JsonNode::long)

    val level: Int
        get() = get("levelFormatted", 0) { it.text().uncolorize().filter(Character::isDigit).toInt() }

    val wins: Int
        get() = get("wins", 0, JsonNode::int)

    val losses: Int
        get() = get("losses", 0, JsonNode::int)

    val kills: Int
        get() = get("kills", 0, JsonNode::int)

    val deaths: Int
        get() = get("deaths", 0, JsonNode::int)

    val souls: Int
        get() = get("souls", 0, JsonNode::int)

    val tokens: Int
        get() = get("cosmetic_tokens", 0, JsonNode::int)

    val timePlayed: Long
        get() = get("time_played", 0, JsonNode::long)

    val rankedRewards: Map<RankedDivision, List<RankedReward>>
        get() = RankedReward.values()
            .filter { it.apiName in packages }
            .groupBy(RankedReward::division)

    fun getWins(mode: SkyWarsMode): Int = get("wins_${mode.apiName}", 0, JsonNode::int)

    fun getLosses(mode: SkyWarsMode): Int = get("losses_${mode.apiName}", 0, JsonNode::int)

    fun getKills(mode: SkyWarsMode): Int = get("kills_${mode.apiName}", 0, JsonNode::int)

    fun getDeaths(mode: SkyWarsMode): Int = get("deaths_${mode.apiName}", 0, JsonNode::int)

    fun getTimePlayed(mode: SkyWarsMode): Long = get("time_played_${mode.apiName}", 0, JsonNode::long)

    fun getWins(kit: SkyWarsKit): Int = get("wins_${kit.apiName}", 0, JsonNode::int)

    fun getLosses(kit: SkyWarsKit): Int = get("losses_${kit.apiName}", 0, JsonNode::int)

    fun getKills(kit: SkyWarsKit): Int = get("kills_${kit.apiName}", 0, JsonNode::int)

    fun getDeaths(kit: SkyWarsKit): Int = get("deaths_${kit.apiName}", 0, JsonNode::int)

    fun getTimePlayed(kit: SkyWarsKit): Long = get("time_played_${kit.apiName}", 0, JsonNode::long)

    fun getActiveCosmetic(cosmetic: CosmeticType): String? =
        get("active_${cosmetic.apiName}", null) {
            it.text()
                .replace(cosmetic.apiName, "")
                .replace("_", " ")
                .replace("-", " ")
                .split(' ')
                .joinToString(" ", transform = String::capitalize)
                .trim()
        }

    fun hasRewards(division: RankedDivision): Boolean = division.rewards.any { it.apiName in packages }

    @Suppress("UNCHECKED_CAST")
    fun <T : SkyWarsKit> getActiveKit(type: SkyWarsType): T? =
        get("activeKit_${type.apiName}", null) { type.kits[it.text()] } as? T

    @Suppress("UNCHECKED_CAST")
    fun <T : SkyWarsKit> getKitsTimePlayed(type: SkyWarsType): Map<T, Long> =
        type.kits
            .map { (it as T) to getTimePlayed(it) }
            .sortedByDescending(Pair<T, Long>::second)
            .toMap()
            .filterValues { it != 0L }

    fun <T : SkyWarsKit> getMostUsedKit(type: SkyWarsType): T? = getKitsTimePlayed<T>(type).keys.firstOrNull()
}
