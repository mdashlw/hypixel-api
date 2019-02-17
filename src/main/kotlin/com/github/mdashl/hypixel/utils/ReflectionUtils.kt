package com.github.mdashl.hypixel.utils

@Suppress("UNCHECKED_CAST")
internal fun <T> Any.getField(name: String): T =
    javaClass.getDeclaredField(name).let {
        it.isAccessible = true

        return it.get(this) as T
    }
