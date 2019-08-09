package ru.mdashlw.hypixel.reply.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mdashlw.hypixel.entities.Guild;
import ru.mdashlw.hypixel.reply.Reply;

public class GuildReply extends Reply<Guild> {
    @JsonProperty
    private Guild guild;

    @Override
    public Guild getValue() {
        return guild;
    }
}
