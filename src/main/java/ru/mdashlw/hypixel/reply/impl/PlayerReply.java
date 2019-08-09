package ru.mdashlw.hypixel.reply.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.entities.player.HypixelPlayer;
import ru.mdashlw.hypixel.reply.Reply;

public class PlayerReply extends Reply<HypixelPlayer> {
    @JsonProperty
    private JsonNode player;

    @Override
    public HypixelPlayer getValue() {
        return player == null || !player.isObject() ? null : new HypixelPlayer((ObjectNode) player);
    }
}
