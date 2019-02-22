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
import com.github.mdashl.hypixel.extensions.children
import com.github.mdashl.hypixel.extensions.uncolorize
import com.github.mdashl.hypixel.utils.LevelingUtils

@JsonDeserialize(using = HypixelPlayerDeserializer::class)
class HypixelPlayer(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {

    val uuid: String by lazy { get("uuid").textValue() }

    val displayname: String by lazy { get("displayname").textValue() }

    val prefix: String? by lazy { get("prefix")?.textValue()?.uncolorize() }

    val rank: Rank by lazy {
        val rank = get("rank")?.textValue()?.takeIf { it != "NORMAL" }
        val monthlyPackageRank = get("monthlyPackageRank")?.textValue()?.takeIf { it != "NONE" }
        val newPackageRank = get("newPackageRank")?.textValue()?.takeIf { it != "NONE" }
        val packageRank = get("packageRank")?.textValue()?.takeIf { it != "NONE" }

        Rank.valueOf(rank ?: monthlyPackageRank ?: newPackageRank ?: packageRank ?: "DEFAULT")
    }

    val networkExp: Long by lazy { get("networkExp")?.longValue() ?: 0 }

    val karma: Long by lazy { get("karma")?.longValue() ?: 0 }

    val achievementPoints: Long by lazy { get("achievementPoints")?.longValue() ?: 0 }

    val firstLogin: Long by lazy { get("firstLogin")?.longValue() ?: 0 }

    val lastLogin: Long by lazy { get("lastLogin")?.longValue() ?: 0 }

    val lastLogout: Long by lazy { get("lastLogout")?.longValue() ?: 0 }

    val knownAliases: List<String> by lazy { get("knownAliases").map(JsonNode::textValue) }

    val highestRankedDivision: RankedDivision? by lazy {
        RankedHat.values().find { it.apiName in vanityMeta.packages }?.toRankedDivision()
    }

    val planckeURL: String = "https://plancke.io/hypixel/player/stats/$uuid"

    val skinURL: String = "https://visage.surgeplay.com/full/$uuid.png"

    val faceURL: String = "https://visage.surgeplay.com/face/$uuid.png"

    val level: Double by lazy { LevelingUtils.getExactLevel(networkExp.toDouble()) }

    val guild: Guild? by lazy { HypixelAPI.getGuildByPlayer(uuid) }

    val isStaff: Boolean by lazy { has("rank") && get("rank")?.textValue() != "NONE" }

    val isOnline: Boolean by lazy { lastLogin != 0L && lastLogout != 0L && lastLogin > lastLogout }

    val formattedDisplayname: String by lazy {
        "[" + (prefix?.let { "$it " } ?: rank.localizedName) + displayname + "]($planckeURL)"
    }

    val vanityMeta: VanityMeta by lazy { VanityMeta((get("vanityMeta") as ObjectNode).children) }

    val stats: Stats by lazy { Stats((get("stats") as ObjectNode).children) }

    class VanityMeta(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {

        val packages: List<String> by lazy { get("packages").map(JsonNode::textValue) }

    }

}
