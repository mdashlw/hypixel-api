package ru.mdashlw.hypixel.api.ranked

import com.fasterxml.jackson.annotation.JsonProperty
import ru.mdashlw.hypixel.api.enums.RankedDivision
import ru.mdashlw.hypixel.api.enums.RankedReward
import java.time.LocalDate

data class RankedSeason(
    val number: Int,
    val leaderboard: Leaderboard? = null,
    @JsonProperty("hiddenInAPI") val isHiddenInAPI: Boolean = false
) {
    val date: LocalDate = RankedHandler.FIRST_SEASON_DATE.plusMonths(number - 1L)

    val hypixelDate: String = date.format(RankedHandler.HYPIXEL_DATE_FORMAT)

    inline val hasLeaderboard: Boolean
        get() = leaderboard != null

    val rewards: Map<RankedDivision, List<RankedReward>> = mapOf(
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

    class Leaderboard : ArrayList<RankedPlayer>() {
        fun getByName(name: String): RankedPlayer? =
            find { it.name == name }

        fun getByUuid(uuid: String): RankedPlayer? =
            find { it.uuid == uuid }
    }
}
