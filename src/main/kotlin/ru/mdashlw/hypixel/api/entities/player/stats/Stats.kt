package ru.mdashlw.hypixel.api.entities.player.stats

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.entities.player.stats.games.SkyWars
import ru.mdashlw.hypixel.api.util.`object`
import ru.mdashlw.hypixel.api.util.children
import ru.mdashlw.hypixel.api.util.get

@Suppress("PropertyName")
class Stats(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val SkyWars: SkyWars?
        get() = get("SkyWars", null) { SkyWars(it.`object`().children) }
}
