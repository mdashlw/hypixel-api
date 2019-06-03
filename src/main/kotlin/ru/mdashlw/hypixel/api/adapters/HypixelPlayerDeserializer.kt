package ru.mdashlw.hypixel.api.adapters

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import ru.mdashlw.hypixel.api.entities.player.HypixelPlayer

object HypixelPlayerDeserializer : JsonDeserializer<HypixelPlayer>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): HypixelPlayer =
        HypixelPlayer(parser.readValueAsTree())
}
