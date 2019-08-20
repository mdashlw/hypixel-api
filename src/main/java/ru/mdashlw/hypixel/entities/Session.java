package ru.mdashlw.hypixel.entities;

import java.util.List;

/**
 * Network session.
 */
public final class Session {
    private GameType gameType;
    private String server;
    private List<String> players;

    /**
     * Game type.
     *
     * @return Game type.
     */
    public GameType getGameType() {
        return this.gameType;
    }

    /**
     * Game server.
     *
     * @return Game server.
     */
    public String getServer() {
        return this.server;
    }

    /**
     * UUIDs of players currently in this session.
     *
     * @return UUIDs of players currently in this session.
     */
    public List<String> getPlayers() {
        return this.players;
    }
}
