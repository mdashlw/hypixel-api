package com.github.mdashl.hypixel.reply

import com.github.mdashl.hypixel.elements.Session

class SessionReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    session: Session?
) : Reply<Session>(success, cause, throttle, session)
