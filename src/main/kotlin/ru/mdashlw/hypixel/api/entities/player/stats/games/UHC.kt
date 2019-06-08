package ru.mdashlw.hypixel.api.entities.player.stats.games

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.entities.player.stats.Game
import ru.mdashlw.hypixel.api.enums.UhcKit
import ru.mdashlw.hypixel.api.enums.UhcTitle
import ru.mdashlw.hypixel.api.util.get
import ru.mdashlw.hypixel.api.util.int
import ru.mdashlw.hypixel.api.util.text

class UHC(obj: ObjectNode) : Game(obj) {
    override val apiName: String = "UHC"
    override val localizedName: String = "UHC"

    val score: Int
        get() = get("score", 0, JsonNode::int)

    val title: UhcTitle
        get() = UhcTitle.values().reversed().find { it.scoreNeeded <= score } ?: UhcTitle.RECRUIT

    val soloWins: Int
        get() = get("wins_solo", 0, JsonNode::int)

    val soloKills: Int
        get() = get("kills_solo", 0, JsonNode::int)

    val soloDeaths: Int
        get() = get("deaths_solo", 0, JsonNode::int)

    val soloHeadsEaten: Int
        get() = get("heads_eaten_solo", 0, JsonNode::int)

    val teamWins: Int
        get() = get("wins", 0, JsonNode::int)

    val teamKills: Int
        get() = get("kills", 0, JsonNode::int)

    val teamDeaths: Int
        get() = get("deaths", 0, JsonNode::int)

    val teamHeadsEaten: Int
        get() = get("heads_eaten", 0, JsonNode::int)

    val equippedKit: UhcKit?
        get() = get("equippedKit", null) { UhcKit.values()[it.text()] }
}
