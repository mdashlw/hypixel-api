package ru.mdashlw.hypixel.elements.player

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.HypixelAPI
import ru.mdashlw.hypixel.adapters.HypixelPlayerDeserializer
import ru.mdashlw.hypixel.elements.Guild
import ru.mdashlw.hypixel.elements.player.stats.Stats
import ru.mdashlw.hypixel.enums.Rank
import ru.mdashlw.hypixel.enums.RankedDivision
import ru.mdashlw.hypixel.enums.RankedHat
import ru.mdashlw.hypixel.ranked.RankedHandler
import ru.mdashlw.hypixel.ranked.RankedSeason
import ru.mdashlw.hypixel.util.LevelingUtil
import ru.mdashlw.hypixel.util.children
import ru.mdashlw.hypixel.util.uncolorize

@JsonDeserialize(using = HypixelPlayerDeserializer::class)
class HypixelPlayer(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val uuid: String
        get() = get("uuid").textValue()

    val displayname: String
        get() = get("displayname").textValue()

    val prefix: String?
        get() = get("prefix")?.textValue()?.let {
            when (HypixelAPI.outputMode) {
                HypixelAPI.OutputMode.RAW, HypixelAPI.OutputMode.MARKDOWN -> it.uncolorize()
                HypixelAPI.OutputMode.COLORIZED -> it
            }
        }

    val rank: Rank
        get() {
            val rank = get("rank")?.textValue()?.takeIf { it != "NORMAL" }
            val monthlyPackageRank = get("monthlyPackageRank")?.textValue()?.takeIf { it != "NONE" }
            val newPackageRank = get("newPackageRank")?.textValue()?.takeIf { it != "NONE" }
            val packageRank = get("packageRank")?.textValue()?.takeIf { it != "NONE" }

            return Rank.valueOf(rank ?: monthlyPackageRank ?: newPackageRank ?: packageRank ?: "DEFAULT")
        }

    val networkExp: Long
        get() = get("networkExp")?.longValue() ?: 0

    val karma: Long
        get() = get("karma")?.longValue() ?: 0

    val achievementPoints: Long
        get() = get("achievementPoints")?.longValue() ?: 0

    val firstLogin: Long
        get() = get("firstLogin")?.longValue() ?: 0

    val lastLogin: Long
        get() = get("lastLogin")?.longValue() ?: 0

    val lastLogout: Long
        get() = get("lastLogout")?.longValue() ?: 0

    val knownAliases: List<String>
        get() = get("knownAliases").map(JsonNode::textValue)

    val highestRankedDivision: RankedDivision?
        get() = vanityMeta?.let { vanityMeta ->
            RankedHat.values().find { it.apiName in vanityMeta.packages }?.toRankedDivision()
        }

    // TODO Optimize and simplify
    val rankedSeasons: Map<RankedSeason, Pair<Int, Int>>
        get() = RankedHandler.seasons
            .asSequence()
            .filter(RankedSeason::isHiddenInAPI)
            .map {
                val player = it.leaderboard?.getByUUID(uuid)
                    ?: return@map null

                it to player.ranked.run { rating to position }
            }
            .plus(
                RankedHandler.seasons
                    .asSequence()
                    .filterNot(RankedSeason::isHiddenInAPI)
                    .map { season ->
                        val game = stats.SkyWars

                        val rating = game.getRating(season)
                        val position = game.getPosition(season)

                        if (rating == 0 && position == 0) {
                            return@map null
                        }

                        season to (rating to position + 1)
                    }
            )
            .filterNotNull()
            .toMap()

    val planckeURL: String
        get() = "https://plancke.io/hypixel/player/stats/$uuid"

    val skinURL: String
        get() = "https://visage.surgeplay.com/full/$uuid.png"

    val faceURL: String
        get() = "https://visage.surgeplay.com/face/$uuid.png"

    val level: Double
        get() = LevelingUtil.getExactLevel(networkExp.toDouble())

    val guild: Guild?
        get() = HypixelAPI.getGuildByPlayer(uuid)

    val isStaff: Boolean
        get() = has("rank") && get("rank")?.textValue() != "NORMAL"

    val isOnline: Boolean
        get() = lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout

    val formattedDisplayname: String
        get() = when (HypixelAPI.outputMode) {
            HypixelAPI.OutputMode.RAW ->
                "${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname"
            HypixelAPI.OutputMode.MARKDOWN ->
                "[${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname]($planckeURL)"
            HypixelAPI.OutputMode.COLORIZED ->
                "${prefix?.let { "$it " } ?: rank.colorizedName}$displayname"
        }

    val vanityMeta: VanityMeta?
        get() = (get("vanityMeta") as? ObjectNode)?.let { VanityMeta(it.children) }

    val stats: Stats
        get() = Stats((get("stats") as ObjectNode).children)

    class VanityMeta(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
        val packages: List<String>
            get() = get("packages").map(JsonNode::textValue)
    }
}
