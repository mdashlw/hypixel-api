package ru.mdashlw.hypixel.api.ranked

import com.fasterxml.jackson.module.kotlin.readValue
import ru.mdashlw.hypixel.api.HypixelApi
import ru.mdashlw.hypixel.api.util.newCall
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

object RankedHandler {
    const val SEASONS_URL = "https://gitlab.com/mdashlw/hypixel-ranked-seasons/raw/master/seasons.json"

    @JvmField
    val FIRST_SEASON_DATE: LocalDate = LocalDate.of(2016, Month.APRIL, 1)

    @JvmField
    val HYPIXEL_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("M_yy")

    lateinit var seasons: List<RankedSeason>

    val currentSeason: RankedSeason
        get() = findSeasonByDate(LocalDate.now())
            ?: throw IllegalStateException("Cannot find current season")

    fun loadSeasons() {
        val response = HypixelApi.okHttpClient.newCall(SEASONS_URL)

        seasons = HypixelApi.jackson.readValue(response)
    }

    fun findSeasonByDate(date: LocalDate): RankedSeason? =
        seasons.find { it.date.year == date.year && it.date.monthValue == date.monthValue }
}
