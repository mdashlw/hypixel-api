package ru.mdashlw.hypixel.api.util

import ru.mdashlw.hypixel.api.interfaces.Kit

operator fun <T : Kit> Array<out T>.get(apiName: String): T? = find { it.apiName == apiName }
