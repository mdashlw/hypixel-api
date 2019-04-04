@file:Suppress("NOTHING_TO_INLINE")

package ru.mdashlw.hypixel.util

@Suppress("UNCHECKED_CAST")
@Throws(NoSuchFieldException::class, TypeCastException::class)
internal inline fun <T> Any.getField(name: String): T =
    javaClass.getDeclaredField(name)
        .apply { isAccessible = true }
        .get(this) as T
