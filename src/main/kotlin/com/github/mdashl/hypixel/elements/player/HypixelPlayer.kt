package com.github.mdashl.hypixel.elements.player

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.mdashl.hypixel.HypixelAPI
import com.github.mdashl.hypixel.adapters.HypixelPlayerDeserializer
import com.github.mdashl.hypixel.elements.Guild
import com.github.mdashl.hypixel.elements.player.stats.Stats
import com.github.mdashl.hypixel.enums.Rank
import com.github.mdashl.hypixel.enums.RankedDivision
import com.github.mdashl.hypixel.enums.RankedHat
import com.github.mdashl.hypixel.enums.RankedSeason
import com.github.mdashl.hypixel.extensions.children
import com.github.mdashl.hypixel.extensions.uncolorize
import com.github.mdashl.hypixel.util.LevelingUtil

@JsonDeserialize(using = HypixelPlayerDeserializer::class)
class HypixelPlayer(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val uuid: String
        get() = get("uuid").textValue()

    val displayname: String
        get() = get("displayname").textValue()

    val prefix: String?
        get() = get("prefix")?.textValue()?.let {
            when (HypixelAPI.mode) {
                HypixelAPI.Mode.UNCOLORIZED -> it.uncolorize()
                HypixelAPI.Mode.COLORIZED -> it
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

    val rankedSeasons: Map<RankedSeason, Pair<Int, Int>>
        get() = RankedSeason.values()
            .copyOfRange(0, 23)
            .filter { it.leaderboard?.get(displayname) != null }
            .map { it to it.leaderboard!!.getValue(displayname) }
            .plus(
                RankedSeason.values()
                    .copyOfRange(22, RankedSeason.CURRENT_SEASON.number + 1)
                    .map { season ->
                        val rating = stats.SkyWars.getRating(season)
                        val position = stats.SkyWars.getPosition(season)

                        if (rating == 0 && position == 0) {
                            return@map null
                        }

                        season to (rating to position + 1)
                    }
                    .filterNotNull()
            )
            .toMap()

    val planckeURL: String = "https://plancke.io/hypixel/player/stats/$uuid"

    val skinURL: String = "https://visage.surgeplay.com/full/$uuid.png"

    val faceURL: String = "https://visage.surgeplay.com/face/$uuid.png"

    val level: Double
        get() = LevelingUtil.getExactLevel(networkExp.toDouble())

    val guild: Guild?
        get() = HypixelAPI.getGuildByPlayer(uuid)

    val isStaff: Boolean
        get() = has("rank") && get("rank")?.textValue() != "NORMAL"

    val isOnline: Boolean
        get() = lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout

    val formattedDisplayname: String
        get() = when (HypixelAPI.mode) {
            HypixelAPI.Mode.UNCOLORIZED ->
                "[${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname]($planckeURL)"
            HypixelAPI.Mode.COLORIZED -> "${prefix?.let { "$it " } ?: rank.colorizedName}$displayname"
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
