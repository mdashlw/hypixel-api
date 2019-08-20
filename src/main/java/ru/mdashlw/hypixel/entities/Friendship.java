package ru.mdashlw.hypixel.entities;

/**
 * Network friendship.
 */
public final class Friendship {
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
        return this._id;
    }

    /**
     * Undashed UUID of the friend request sender.
     *
     * @return Undashed UUID of the friend request sender.
     */
    public String getSender() {
        return this.uuidSender;
    }

    /**
     * Undashed UUID of the friend request receiver.
     *
     * @return Undashed UUID of the friend request receiver.
     */
    public String getReceiver() {
        return this.uuidReceiver;
    }

    /**
     * Time when this friendship started.
     *
     * @return Time when this friendship started.
     */
    public long getStarted() {
        return this.started;
    }
}
