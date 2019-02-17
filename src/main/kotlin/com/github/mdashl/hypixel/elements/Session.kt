package com.github.mdashl.hypixel.elements

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.mdashl.hypixel.enums.GameType

data class Session(
    @JsonProperty("gameType") val game: GameType,
    val server: String,
    val players: List<String>
)
