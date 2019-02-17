package com.github.mdashl.hypixel.extensions

internal fun String.uncolorize(): String = replace("§.".toRegex(), "")
