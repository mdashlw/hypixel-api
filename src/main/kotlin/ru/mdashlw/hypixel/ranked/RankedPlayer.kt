package ru.mdashlw.hypixel.ranked

data class RankedPlayer(val name: String, val uuid: String, val ranked: Ranked) {
    data class Ranked(val rating: Int, val position: Int)
}
