package com.github.mdashl.hypixel.elements

import com.github.mdashl.hypixel.enums.GameType

data class Session(
    val gameType: GameType,
    val server: String,
    val players: List<String>
)
