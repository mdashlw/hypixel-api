package ru.mdashlw.hypixel.util

// Taken from Plancke/hypixel-php
/**
 * @author Plancke
 */
class GuildLevelingUtil private constructor() {
    companion object {
        @JvmStatic
        private val EXP_NEEDED = listOf(
            100000,
            150000,
            250000,
            500000,
            750000,
            1000000,
            1250000,
            1500000,
            2000000,
            2500000,
            2500000,
            2500000,
            2500000,
            2500000,
            3000000
        )

        @JvmStatic
        @Suppress("NAME_SHADOWING")
        fun getLevel(exp: Long): Int {
            var exp = exp

            var level = 0

            for (i in 0..100) {
                val need = if (i >= EXP_NEEDED.size) EXP_NEEDED.last() else EXP_NEEDED[i]

                exp -= need

                if (exp < 0) {
                    return level
                } else {
                    level++
                }
            }

            throw IllegalStateException()
        }
    }
}
