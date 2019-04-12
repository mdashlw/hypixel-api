package ru.mdashlw.hypixel.reply.impl

import ru.mdashlw.hypixel.elements.player.HypixelPlayer
import ru.mdashlw.hypixel.reply.Reply

class PlayerReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    player: HypixelPlayer?
) : Reply<HypixelPlayer>(success, cause, throttle, player)
