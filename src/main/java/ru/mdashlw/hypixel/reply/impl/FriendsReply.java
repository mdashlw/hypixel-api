package ru.mdashlw.hypixel.reply.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mdashlw.hypixel.entities.Friendship;
import ru.mdashlw.hypixel.reply.Reply;

import java.util.List;

public final class FriendsReply extends Reply<List<Friendship>> {
    @JsonProperty
    private List<Friendship> records;

    @Override
    public List<Friendship> getValue() {
        return this.records;
    }
}
