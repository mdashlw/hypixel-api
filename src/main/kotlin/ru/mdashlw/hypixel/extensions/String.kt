@file:Suppress("NOTHING_TO_INLINE")

package ru.mdashlw.hypixel.extensions

@JvmField
val COLOR_PATTERN = "§.".toRegex()

inline fun String.uncolorize(): String = replace(COLOR_PATTERN, "")
