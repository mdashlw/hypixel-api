package com.github.mdashl.hypixel.reply

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.mdashl.hypixel.elements.Session

class SessionReply(
    throttle: Boolean,
    success: Boolean,
    cause: String?
) : Reply<Session>(throttle, success, cause) {

    @JsonProperty("session")
    override val element: Session? = null

}
