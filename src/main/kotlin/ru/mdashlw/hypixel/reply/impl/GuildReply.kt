package ru.mdashlw.hypixel.reply.impl

import ru.mdashlw.hypixel.elements.Guild
import ru.mdashlw.hypixel.reply.Reply

class GuildReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    guild: Guild?
) : Reply<Guild>(success, cause, throttle, guild)
