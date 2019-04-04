package ru.mdashlw.hypixel.reply

import ru.mdashlw.hypixel.elements.Guild

class GuildReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    guild: Guild?
) : Reply<Guild>(success, cause, throttle, guild)
