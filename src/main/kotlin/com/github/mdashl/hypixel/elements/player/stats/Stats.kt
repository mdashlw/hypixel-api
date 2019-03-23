package com.github.mdashl.hypixel.elements.player.stats

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.mdashl.hypixel.elements.player.stats.games.SkyWars
import com.github.mdashl.hypixel.extensions.children

class Stats(children: Map<String, JsonNode>) : ObjectNode(JsonNodeFactory.instance, children) {
    val SkyWars: SkyWars
        get() = SkyWars((get("SkyWars") as ObjectNode).children)
}
