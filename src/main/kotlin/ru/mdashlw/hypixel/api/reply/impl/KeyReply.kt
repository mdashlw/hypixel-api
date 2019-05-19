package ru.mdashlw.hypixel.api.reply.impl

import ru.mdashlw.hypixel.api.entities.Key
import ru.mdashlw.hypixel.api.reply.Reply

class KeyReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    record: Key?
) : Reply<Key>(success, cause, throttle, record)
