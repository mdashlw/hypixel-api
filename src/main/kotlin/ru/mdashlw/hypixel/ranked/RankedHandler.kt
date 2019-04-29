package ru.mdashlw.hypixel.ranked

import com.fasterxml.jackson.module.kotlin.readValue
import ru.mdashlw.hypixel.HypixelAPI
import ru.mdashlw.hypixel.util.newCall
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

object RankedHandler {
    private const val SEASONS_URL =
        "https://raw.githubusercontent.com/mdashlw/hypixel-ranked-seasons/master/seasons.json"

    @JvmField
    val FIRST_SEASON_DATE: LocalDate = LocalDate.of(2016, Month.APRIL, 1)

    /**
     * @sample 1_19
     * @sample 12_18
     */
    @JvmField
    val HYPIXEL_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("M_yy")

    lateinit var seasons: List<RankedSeason>

    val currentSeason: RankedSeason
        get() = findSeasonByDate(LocalDate.now())
            ?: throw IllegalStateException("Cannot find current season")

    fun loadSeasons() {
        val response = HypixelAPI.okHttpClient.newCall(SEASONS_URL)

        seasons = HypixelAPI.jackson.readValue(response)
    }

    fun findSeasonByDate(date: LocalDate): RankedSeason? =
        seasons.find { it.date.year == date.year && it.date.monthValue == date.monthValue }
}
