package ru.mdashlw.hypixel.enums

enum class RankedHat(val apiName: String) {
    ENDER("hat_ender_steve"),
    DIAMOND("hat_diamond_steve"),
    GOLD("hat_gold_steve"),
    IRON("hat_iron_steve"),
    STONE("hat_stone_steve"),
    WOOD("hat_wood_steve");

    fun toRankedDivision(): RankedDivision =
        when (this) {
            RankedHat.ENDER -> RankedDivision.MASTERS
            RankedHat.DIAMOND -> RankedDivision.DIAMOND
            RankedHat.GOLD -> RankedDivision.GOLD
            RankedHat.IRON -> RankedDivision.IRON
            RankedHat.STONE -> RankedDivision.STONE
            RankedHat.WOOD -> RankedDivision.WOOD
        }
}
