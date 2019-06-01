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
import ru.mdashlw.hypixel.api.util.*

@JsonDeserialize(using = HypixelPlayerDeserializer::class)
class HypixelPlayer(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val uuid: String
        get() = get("uuid", JsonNode::text)

    val displayname: String
        get() = get("displayname", "unknown", JsonNode::text)

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
            val rank = get("rank", null, JsonNode::text).takeUnless("NORMAL"::equals)
            val monthlyPackageRank = get("monthlyPackageRank", null, JsonNode::text).takeUnless("NONE"::equals)
            val newPackageRank = get("newPackageRank", null, JsonNode::text).takeUnless("NONE"::equals)
            val packageRank = get("packageRank", null, JsonNode::text).takeUnless("NONE"::equals)

            return (rank ?: monthlyPackageRank ?: newPackageRank ?: packageRank)?.let(Rank::valueOf) ?: Rank.DEFAULT
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

    val achievements: Map<String, Int>
        get() = get("achievements", emptyMap()) { it.obj().children.mapValues { it.value.int() } }

    val questsCompleted: Int
        get() = achievements.getOrDefault("general_quest_master", 0)

    val planckeUrl: String
        get() = "https://plancke.io/hypixel/player/stats/$uuid"

    val skinUrl: String
        get() = "https://visage.surgeplay.com/full/$uuid.png"

    val faceUrl: String
        get() = "https://visage.surgeplay.com/face/$uuid.png"

    val level: Double
        get() = LevelingUtil.getExactLevel(networkExp.toDouble())

    val guild: Guild?
        get() = HypixelApi.retrieveGuildByPlayer(uuid)

    val isStaff: Boolean
        get() = get("rank", "NORMAL", JsonNode::text) != "NORMAL"

    val isOnline: Boolean
        get() = lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout

    val formattedDisplayname: String
        get() = when (HypixelApi.outputMode) {
            HypixelApi.OutputMode.RAW ->
                "${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname"
            HypixelApi.OutputMode.MARKDOWN ->
                "[${prefix?.let { "$it " } ?: rank.uncolorizedName}$displayname]($planckeUrl)"
            HypixelApi.OutputMode.COLORIZED ->
                "${prefix?.let { "$it " } ?: rank.colorizedName}$displayname"
        }

    val vanityMeta: VanityMeta?
        get() = get("vanityMeta", null) { VanityMeta(it.obj().children) }

    val stats: Stats?
        get() = get("stats", null) { Stats(it.obj().children) }

    class VanityMeta(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
        val packages: List<String>
            get() = get("packages", emptyList()) { it.map(JsonNode::textValue) }
    }
}
