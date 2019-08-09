package ru.mdashlw.hypixel.entities;

import java.util.List;

/**
 * Network session.
 */
public class Session {
    private GameType gameType;
    private String server;
    private List<String> players;

    /**
     * Game type.
     *
     * @return Game type.
     */
    public GameType getGameType() {
        return gameType;
    }

    /**
     * Game server.
     *
     * @return Game server.
     */
    public String getServer() {
        return server;
    }

    /**
     * UUIDs of players currently in this session.
     *
     * @return UUIDs of players currently in this session.
     */
    public List<String> getPlayers() {
        return players;
    }
}
