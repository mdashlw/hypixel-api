package com.github.mdashl.hypixel.reply

import com.github.mdashl.hypixel.elements.player.HypixelPlayer

class PlayerReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    player: HypixelPlayer?
) : Reply<HypixelPlayer>(success, cause, throttle, player)
