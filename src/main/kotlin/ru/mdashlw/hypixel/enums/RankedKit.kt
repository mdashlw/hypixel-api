package ru.mdashlw.hypixel.enums

enum class RankedKit(val apiName: String, val localizedName: String) {
    DEFAULT("kit_ranked_ranked_default", "Default"),
    SCOUT("kit_ranked_ranked_scout", "Scout"),
    BLACKSMITH("kit_blacksmith_ranked_blacksmith", "Blacksmith"),
    ARMORER("kit_ranked_ranked_armorer", "Armorer"),
    CHAMPION("kit_ranked_ranked_champion", "Champion"),
    ATHLETE("kit_ranked_ranked_athlete", "Athlete"),
    PALADIN("kit_ranked_ranked_paladin", "Paladin"),
    BOWMAN("kit_ranked_ranked_bowman", "Bowman"),
    MAGICIAN("kit_ranked_ranked_magician", "Magician"),
    HEALER("kit_ranked_ranked_healer", "Healer"),
    PYROMANCER("kit_ranked_ranked_pyromancer", "Pyromancer");

    override fun toString(): String = localizedName

    companion object {
        operator fun get(apiName: String): RankedKit? = values().find { it.apiName == apiName }
    }
}
