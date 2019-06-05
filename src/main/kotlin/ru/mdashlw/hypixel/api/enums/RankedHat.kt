package ru.mdashlw.hypixel.api.enums

enum class RankedHat(val apiName: String, val division: RankedDivision) {
    ENDER("hat_ender_steve", RankedDivision.MASTERS),
    DIAMOND("hat_diamond_steve", RankedDivision.DIAMOND),
    GOLD("hat_gold_steve", RankedDivision.GOLD),
    IRON("hat_iron_steve", RankedDivision.IRON),
    STONE("hat_stone_steve", RankedDivision.STONE),
    WOOD("hat_wood_steve", RankedDivision.WOOD);
}
