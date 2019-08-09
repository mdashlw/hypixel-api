package ru.mdashlw.hypixel.reply.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mdashlw.hypixel.entities.Key;
import ru.mdashlw.hypixel.reply.Reply;

public class KeyReply extends Reply<Key> {
    @JsonProperty
    private Key record;

    @Override
    public Key getValue() {
        return record;
    }
}
