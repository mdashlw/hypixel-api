package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.kits.SkyWarsKit

enum class SkyWarsMegaKit(override val apiName: String, override val localizedName: String) : SkyWarsKit {
    DEFAULT("kit_mega_mega_default", "Default"),
    ARMORER("kit_mega_mega_armorer", "Armorer"),
    ARMORSMITH("kit_mega_mega_armorsmith", "Armorsmith"),
    BASEBALL_PLAYER("kit_mega_mega_baseball-player", "Baseball Player"),
    CANNONEER("kit_mega_mega_cannoneer", "Cannoneer"),
    HEALER("kit_mega_mega_healer", "Healer"),
    HUNTER("kit_mega_mega_hunter", "Hunter"),
    KNIGHT("kit_mega_mega_knight", "Knight"),
    PALADIN("kit_mega_mega_paladin", "Paladin"),
    SCOUT("kit_mega_mega_scout", "Scout"),
    SKELETOR("kit_mega_mega_skeletor", "Skeletor"),
    WITCH("kit_mega_mega_witch", "Witch"),
    HELLBOUND("kit_mega_mega_hellhound", "Hellbound"),
    FISHERMAN("kit_mega_mega_fisherman", "Fisherman"),
    PYROMANIAC("kit_mega_mega_pyromaniac", "Pyromaniac");

    override fun toString(): String = localizedName
}
