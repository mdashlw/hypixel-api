package com.github.mdashl.hypixel.reply

import com.github.mdashl.hypixel.elements.Guild

class GuildReply(
    success: Boolean,
    cause: String?,
    throttle: Boolean,
    guild: Guild?
) : Reply<Guild>(success, cause, throttle, guild)
