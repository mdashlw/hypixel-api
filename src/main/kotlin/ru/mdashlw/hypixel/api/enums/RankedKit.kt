package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.kits.SkyWarsKit

enum class RankedKit(override val apiName: String, override val localizedName: String) : SkyWarsKit {
    DEFAULT("kit_ranked_ranked_default", "Default"),
    SCOUT("kit_ranked_ranked_scout", "Scout"),
    MAGICIAN("kit_ranked_ranked_magician", "Magician"),
    ARMORER("kit_ranked_ranked_armorer", "Armorer"),
    CHAMPION("kit_ranked_ranked_champion", "Champion"),
    BOWMAN("kit_ranked_ranked_bowman", "Bowman"),
    ATHLETE("kit_ranked_ranked_athlete", "Athlete"),
    BLACKSMITH("kit_blacksmith_ranked_blacksmith", "Blacksmith"),
    HEALER("kit_ranked_ranked_healer", "Healer"),
    PYROMANCER("kit_ranked_ranked_pyromancer", "Pyromancer"),
    HOUND("kit_ranked_ranked_hound", "Hound"),
    PALADIN("kit_ranked_ranked_paladin", "Paladin");

    override fun toString(): String = localizedName
}
