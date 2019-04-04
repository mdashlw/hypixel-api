package ru.mdashlw.hypixel.reply

import ru.mdashlw.hypixel.elements.Session

class SessionReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    session: Session?
) : Reply<Session>(success, cause, throttle, session)
