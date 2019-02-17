package com.github.mdashl.hypixel.reply

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.mdashl.hypixel.elements.Guild

class GuildReply(
    throttle: Boolean,
    success: Boolean,
    cause: String?
) : Reply<Guild>(throttle, success, cause) {

    @JsonProperty("guild")
    override val element: Guild? = null

}
