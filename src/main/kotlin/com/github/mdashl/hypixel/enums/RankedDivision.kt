package com.github.mdashl.hypixel.enums

enum class RankedDivision(val localizedName: String) {

    MASTERS("Masters"),
    DIAMOND("Diamond"),
    GOLD("Gold"),
    IRON("Iron"),
    STONE("Stone"),
    WOOD("Wood");

    val rewards: List<RankedReward> by lazy { RankedReward.values().filter { it.division == this } }

    override fun toString(): String = localizedName

}
