package ru.mdashlw.hypixel.api.util

object LevelingUtil {
    private const val BASE = 10000.0
    private const val GROWTH = 2500.0

    private const val HALF_GROWTH = 0.5 * GROWTH

    private const val REVERSE_PQ_PREFIX = -(BASE - 0.5 * GROWTH) / GROWTH
    private const val REVERSE_CONST = REVERSE_PQ_PREFIX * REVERSE_PQ_PREFIX
    private const val GROWTH_DIVIDES_2 = 2 / GROWTH

    fun getLevel(exp: Double): Double =
        if (exp < 0) 1.0 else Math.floor(1.0 + REVERSE_PQ_PREFIX + Math.sqrt(REVERSE_CONST + GROWTH_DIVIDES_2 * exp))

    fun getExactLevel(exp: Double): Double = getLevel(exp) + getPercentageToNextLevel(exp)

    fun getTotalExpToLevel(level: Double): Double {
        val lv = Math.floor(level)
        val x0 = getTotalExpToFullLevel(lv)

        return if (level == lv) x0 else (getTotalExpToFullLevel(lv + 1) - x0) * (level % 1) + x0
    }

    fun getTotalExpToFullLevel(level: Double): Double = (HALF_GROWTH * (level - 2) + BASE) * (level - 1)

    fun getPercentageToNextLevel(exp: Double): Double {
        val lv = getLevel(exp)
        val x0 = getTotalExpToLevel(lv)

        return (exp - x0) / (getTotalExpToLevel(lv + 1) - x0)
    }
}
