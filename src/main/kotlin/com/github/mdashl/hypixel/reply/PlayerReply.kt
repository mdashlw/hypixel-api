package com.github.mdashl.hypixel.reply

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.mdashl.hypixel.elements.player.HypixelPlayer

class PlayerReply(
    throttle: Boolean,
    success: Boolean,
    cause: String?
) : Reply<HypixelPlayer>(throttle, success, cause) {

    @JsonProperty("player")
    override val element: HypixelPlayer? = null

}
