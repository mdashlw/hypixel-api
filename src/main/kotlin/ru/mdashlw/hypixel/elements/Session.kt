package ru.mdashlw.hypixel.elements

import com.fasterxml.jackson.annotation.JsonProperty
import ru.mdashlw.hypixel.enums.GameType

data class Session(
    @JsonProperty("gameType") val game: GameType,
    val server: String,
    val players: List<String>
)
