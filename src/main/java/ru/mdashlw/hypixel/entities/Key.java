package ru.mdashlw.hypixel.entities;

/**
 * Network API key.
 */
public final class Key {
    private String ownerUuid;
    private String key;
    private int totalQueries;
    private int queriesInPastMin;

    /**
     * Undashed owner UUID.
     *
     * @return Undashed owner UUID.
     */
    public String getOwnerUuid() {
        return this.ownerUuid;
    }

    /**
     * Key with dashes.
     *
     * @return Key with dashes.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Total queries.
     *
     * @return Total queries.
     */
    public int getTotalQueries() {
        return this.totalQueries;
    }

    /**
     * Queries in the past minute.
     *
     * @return Queries in the past minute.
     */
    public int getQueriesInPastMin() {
        return this.queriesInPastMin;
    }
}
