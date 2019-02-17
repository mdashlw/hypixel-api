package com.github.mdashl.hypixel.enums

import java.time.LocalDate

enum class RankedSeason(val date: String, val number: Int) {

    SEASON_1("4_16", 1),
    SEASON_2("5_16", 2),
    SEASON_3("6_16", 3),
    SEASON_4("7_16", 4),
    SEASON_5("8_16", 5),
    SEASON_6("9_16", 6),
    SEASON_7("10_16", 7),
    SEASON_8("11_16", 8),
    SEASON_9("12_16", 9),
    SEASON_10("1_17", 10),
    SEASON_11("2_17", 11),
    SEASON_12("3_17", 12),
    SEASON_13("4_17", 13),
    SEASON_14("5_17", 14),
    SEASON_15("6_17", 15),
    SEASON_16("7_17", 16),
    SEASON_17("8_17", 17),
    SEASON_18("9_17", 18),
    SEASON_19("10_17", 19),
    SEASON_20("11_17", 20),
    SEASON_21("12_17", 21),
    SEASON_22("1_18", 22),
    SEASON_23("2_18", 23),
    SEASON_24("3_18", 24),
    SEASON_25("4_18", 25),
    SEASON_26("5_18", 26),
    SEASON_27("6_18", 27),
    SEASON_28("7_18", 28),
    SEASON_29("8_18", 29),
    SEASON_30("9_18", 30),
    SEASON_31("10_18", 31),
    SEASON_32("11_18", 32),
    SEASON_33("12_18", 33),
    SEASON_34("1_19", 34),
    SEASON_35("2_19", 35),
    SEASON_36("3_19", 36),
    SEASON_37("4_19", 37),
    SEASON_38("5_19", 38),
    SEASON_39("6_19", 39),
    SEASON_40("7_19", 40),
    SEASON_41("8_19", 41),
    SEASON_42("9_19", 42),
    SEASON_43("10_19", 43),
    SEASON_44("11_19", 44),
    SEASON_45("12_19", 45),
    SEASON_46("1_20", 46),
    SEASON_47("2_20", 47),
    SEASON_48("3_20", 48),
    SEASON_49("4_20", 49),
    SEASON_50("5_20", 50);

    val rewards: Map<RankedDivision, List<RankedReward>> by lazy {
        mapOf(
            RankedDivision.MASTERS to listOf(
                RankedReward.DRAGON_RIDER,
                RankedDivision.MASTERS.rewards[number % 2]
            ),
            RankedDivision.DIAMOND to listOf(
                RankedReward.MAGIC_BOX,
                RankedDivision.DIAMOND.rewards[number % 3],
                RankedDivision.DIAMOND.rewards[number % 4 + 3]
            ),
            RankedDivision.GOLD to listOf(
                RankedDivision.GOLD.rewards[number % 3]
            )
        )
    }

    companion object {

        val CURRENT_SEASON: RankedSeason by lazy {
            val date = LocalDate.now()

            get("${date.monthValue}_${date.year.toString().drop(2)}")
        }

        operator fun get(date: String): RankedSeason = values().find { it.date == date }!!

    }

}
