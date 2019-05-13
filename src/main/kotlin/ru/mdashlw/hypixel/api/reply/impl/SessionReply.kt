package ru.mdashlw.hypixel.api.reply.impl

import ru.mdashlw.hypixel.api.elements.Session
import ru.mdashlw.hypixel.api.reply.Reply

class SessionReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    session: Session?
) : Reply<Session>(success, cause, throttle, session)
