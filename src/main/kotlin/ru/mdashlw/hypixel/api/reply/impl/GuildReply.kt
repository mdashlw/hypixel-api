package ru.mdashlw.hypixel.api.reply.impl

import ru.mdashlw.hypixel.api.elements.Guild
import ru.mdashlw.hypixel.api.reply.Reply

class GuildReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    guild: Guild?
) : Reply<Guild>(success, cause, throttle, guild)
