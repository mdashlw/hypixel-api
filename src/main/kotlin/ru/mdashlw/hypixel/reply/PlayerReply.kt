package ru.mdashlw.hypixel.reply

import ru.mdashlw.hypixel.elements.player.HypixelPlayer

class PlayerReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    player: HypixelPlayer?
) : Reply<HypixelPlayer>(success, cause, throttle, player)
