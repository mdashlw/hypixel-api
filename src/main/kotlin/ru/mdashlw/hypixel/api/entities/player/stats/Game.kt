package ru.mdashlw.hypixel.api.entities.player.stats

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.util.CustomObjectNode
import ru.mdashlw.hypixel.api.util.get
import ru.mdashlw.hypixel.api.util.int
import ru.mdashlw.hypixel.api.util.text

abstract class Game(obj: ObjectNode) : CustomObjectNode(obj) {
    abstract val apiName: String
    abstract val localizedName: String

    val packages: List<String>
        get() = get("packages", emptyList()) { it.map(JsonNode::text) }

    val coins: Int
        get() = get("coins", 0, JsonNode::int)
}
