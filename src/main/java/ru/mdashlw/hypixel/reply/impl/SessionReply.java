package ru.mdashlw.hypixel.reply.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mdashlw.hypixel.entities.Session;
import ru.mdashlw.hypixel.reply.Reply;

public class SessionReply extends Reply<Session> {
    @JsonProperty
    private Session session;

    @Override
    public Session getValue() {
        return session;
    }
}
