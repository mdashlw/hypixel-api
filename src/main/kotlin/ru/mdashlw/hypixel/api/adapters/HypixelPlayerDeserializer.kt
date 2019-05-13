package ru.mdashlw.hypixel.api.adapters

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.elements.player.HypixelPlayer
import ru.mdashlw.hypixel.api.util.children

object HypixelPlayerDeserializer : JsonDeserializer<HypixelPlayer>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): HypixelPlayer =
        HypixelPlayer(parser.readValueAsTree<ObjectNode>().children)
}
