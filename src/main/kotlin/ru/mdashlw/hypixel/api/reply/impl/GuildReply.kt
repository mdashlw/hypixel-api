package ru.mdashlw.hypixel.api.reply.impl

import ru.mdashlw.hypixel.api.entities.Guild
import ru.mdashlw.hypixel.api.reply.Reply

class GuildReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    guild: Guild?
) : Reply<Guild>(success, cause, throttle, guild)
