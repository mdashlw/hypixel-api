package ru.mdashlw.hypixel.api.entities.player

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.HypixelApi
import ru.mdashlw.hypixel.api.adapters.HypixelPlayerDeserializer
import ru.mdashlw.hypixel.api.entities.Guild
import ru.mdashlw.hypixel.api.entities.player.stats.Stats
import ru.mdashlw.hypixel.api.enums.Rank
import ru.mdashlw.hypixel.api.enums.RankedDivision
import ru.mdashlw.hypixel.api.enums.RankedHat
import ru.mdashlw.hypixel.api.extensions.uncolorize
import ru.mdashlw.hypixel.api.ranked.RankedHandler
import ru.mdashlw.hypixel.api.ranked.RankedSeason
import ru.mdashlw.hypixel.api.util.*

@JsonDeserialize(using = HypixelPlayerDeserializer::class)
class HypixelPlayer(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val uuid: String
        get() = get("uuid", JsonNode::text)

    val displayname: String
        get() = get("displayname", JsonNode::text)

    val prefix: String?
        get() = get<String?>("prefix", null) {
            it.text().run {
                when (HypixelApi.outputMode) {
                    HypixelApi.OutputMode.RAW, HypixelApi.OutputMode.MARKDOWN -> uncolorize()
                    HypixelApi.OutputMode.COLORIZED -> this
                }
            }
        }

    val rank: Rank
        get() {
            val rank = get("rank", null, JsonNode::text).takeIf { it != "NORMAL" }
            val monthlyPackageRank = get("monthlyPackageRank", null, JsonNode::text).takeIf { it != "NONE" }
            val newPackageRank = get("newPackageRank", null, JsonNode::text).takeIf { it != "NONE" }
            val packageRank = get("packageRank", null, JsonNode::text).takeIf { it != "NONE" }

            return Rank.valueOf(rank ?: monthlyPackageRank ?: newPackageRank ?: packageRank ?: "DEFAULT")
        }

    val networkExp: Long
        get() = get("networkExp", 0, JsonNode::long)

    val karma: Long
        get() = get("karma", 0, JsonNode::long)

    val achievementPoints: Long
        get() = get("achievementPoints", 0, JsonNode::long)

    val firstLogin: Long
        get() = get("firstLogin", 0, JsonNode::long)

    val lastLogin: Long
        get() = get("lastLogin", 0, JsonNode::long)

    val lastLogout: Long
        get() = get("lastLogout", 0, JsonNode::long)

    val knownAliases: List<String>
        get() = get("knownAliases", emptyList()) { it.map(JsonNode::text) }

    val highestRankedDivision: RankedDivision?
        get() = vanityMeta?.run { RankedHat.values().find { it.apiName in packages }?.toRankedDivision() }

    // TODO Optimize and simplify
    val rankedSeasons: Map<RankedSeason, Pair<Int, Int>>
        get() = RankedHandler.seasons
            .asSequence()
            .filter(RankedSeason::isHiddenInAPI)
            .map {
                val player = it.leaderboard?.getByUuid(uuid)
                    ?: return@map null

                it to player.ranked.run { rating to position }
            }
            .plus(
                RankedHandler.seasons
                    .asSequence()
                    .filterNot(RankedSeason::isHiddenInAPI)
                    .map { season ->
                        val game = stats?.SkyWars ?: return@map null

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

    val achievements: Map<String, Int>
        get() = get("achievements", emptyMap()) { it.`object`().children.mapValues { it.value.int() } }

    val questsCompleted: Int
        get() = achievements.getOrDefault("general_quest_master", 0)

    val planckeURL: String
        get() = "https://plancke.io/hypixel/player/stats/$uuid"

    val skinURL: String
        get() = "https://visage.surgeplay.com/full/$uuid.png"

    val faceURL: String
        get() = "https://visage.surgeplay.com/face/$uuid.png"

    val level: Double
        get() = LevelingUtil.getExactLevel(networkExp.toDouble())

    val guild: Guild?
        get() = HypixelApi.getGuildByPlayer(uuid)

    val isStaff: Boolean
        get() = get("rank", "NORMAL", JsonNode::text) != "NORMAL"

    val isOnline: Boolean
        get() = lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout

    val formattedDisplayname: String
        get() = when (HypixelApi.outputMode) {
            HypixelApi.OutputMode.RAW ->
                "${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname"
            HypixelApi.OutputMode.MARKDOWN ->
                "[${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname]($planckeURL)"
            HypixelApi.OutputMode.COLORIZED ->
                "${prefix?.let { "$it " } ?: rank.colorizedName}$displayname"
        }

    val vanityMeta: VanityMeta?
        get() = get("vanityMeta", null) { VanityMeta(it.`object`().children) }

    val stats: Stats?
        get() = get("stats", null) { Stats(it.`object`().children) }

    class VanityMeta(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
        val packages: List<String>
            get() = get("packages", emptyList()) { it.map(JsonNode::textValue) }
    }
}
