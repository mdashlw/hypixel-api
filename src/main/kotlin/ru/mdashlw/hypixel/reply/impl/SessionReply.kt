package ru.mdashlw.hypixel.reply.impl

import ru.mdashlw.hypixel.elements.Session
import ru.mdashlw.hypixel.reply.Reply

class SessionReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    session: Session?
) : Reply<Session>(success, cause, throttle, session)
