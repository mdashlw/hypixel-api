package com.github.mdashl.hypixel.util

@Suppress("UNCHECKED_CAST")
@Throws(NoSuchFieldException::class, TypeCastException::class)
internal fun <T> Any.getField(name: String): T =
    javaClass.getDeclaredField(name)
        .apply { isAccessible = true }
        .get(this) as T
