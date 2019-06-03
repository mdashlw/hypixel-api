package ru.mdashlw.hypixel.api.entities.player.stats

import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.hypixel.api.entities.player.stats.games.SkyWars
import ru.mdashlw.hypixel.api.util.CustomObjectNode
import ru.mdashlw.hypixel.api.util.get
import ru.mdashlw.hypixel.api.util.obj

@Suppress("PropertyName")
class Stats(obj: ObjectNode) : CustomObjectNode(obj) {
    val SkyWars: SkyWars?
        get() = get("SkyWars", null) { SkyWars(it.obj()) }
}
