package com.github.mdashl.hypixel.adapters

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.mdashl.hypixel.elements.player.HypixelPlayer
import com.github.mdashl.hypixel.extensions.children

object HypixelPlayerDeserializer : JsonDeserializer<HypixelPlayer>() {

    override fun deserialize(parser: JsonParser, context: DeserializationContext): HypixelPlayer =
        HypixelPlayer(parser.readValueAsTree<ObjectNode>().children)

}
