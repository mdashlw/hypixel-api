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
            ENDER -> RankedDivision.MASTERS
            DIAMOND -> RankedDivision.DIAMOND
            GOLD -> RankedDivision.GOLD
            IRON -> RankedDivision.IRON
            STONE -> RankedDivision.STONE
            WOOD -> RankedDivision.WOOD
        }
}
