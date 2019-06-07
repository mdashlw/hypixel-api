package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.Kit

enum class SkyWarsType(val apiName: String, val localizedName: String, val kits: Array<out Kit>) {
    NORMAL("SOLO", "Normal", SkyWarsNormalKit.values()),
    INSANE("TEAMS", "Insane", SkyWarsInsaneKit.values()),
    MEGA("MEGA", "Mega", SkyWarsMegaKit.values()),
    RANKED("RANKED", "Ranked", RankedKit.values());

    override fun toString(): String = localizedName
}
