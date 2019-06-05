package ru.mdashlw.hypixel.api.enums

enum class SkyWarsMode(val apiName: String, val localizedName: String) {
    SOLO("solo", "Solo"),
    SOLO_NORMAL("solo_normal", "Solo Normal"),
    SOLO_INSANE("solo_insane", "Solo Insane"),
    DOUBLES("team", "Doubles"),
    DOUBLES_NORMAL("team_normal", "Doubles Normal"),
    DOUBLES_INSANE("team_insane", "Doubles Insane"),
    MEGA("mega", "Mega"),
    RANKED("ranked", "Ranked");

    override fun toString(): String = localizedName
}
