package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.Kit

enum class SkyWarsType(val apiName: String, val localizedName: String, val kits: Array<out Kit>) {
    NORMAL("SOLO", "Normal", emptyArray()), // TODO Kits
    INSANE("TEAMS", "Insane", emptyArray()), // TODO Kits
    MEGA("MEGA", "Mega", emptyArray()), // TODO Kits
    RANKED("RANKED", "Ranked", RankedKit.values());

    override fun toString(): String = localizedName
}
