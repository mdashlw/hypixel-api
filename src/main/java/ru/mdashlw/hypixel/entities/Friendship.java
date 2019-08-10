package ru.mdashlw.hypixel.entities;

/**
 * Network friendship.
 */
public class Friendship {
    private String _id;
    private String uuidSender;
    private String uuidReceiver;
    private long started;

    /**
     * Mongo object id.
     *
     * @return Mongo object id.
     */
    public String getId() {
        return _id;
    }

    /**
     * Undashed UUID of the friend request sender.
     *
     * @return Undashed UUID of the friend request sender.
     */
    public String getUuidSender() {
        return uuidSender;
    }

    /**
     * Undashed UUID of the friend request receiver.
     *
     * @return Undashed UUID of the friend request receiver.
     */
    public String getUuidReceiver() {
        return uuidReceiver;
    }

    /**
     * Time when this friendship started.
     *
     * @return Time when this friendship started.
     */
    public long getStarted() {
        return started;
    }
}
