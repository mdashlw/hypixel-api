package ru.mdashlw.hypixel.api.reply.impl

import ru.mdashlw.hypixel.api.elements.player.HypixelPlayer
import ru.mdashlw.hypixel.api.reply.Reply

class PlayerReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    player: HypixelPlayer?
) : Reply<HypixelPlayer>(success, cause, throttle, player)
