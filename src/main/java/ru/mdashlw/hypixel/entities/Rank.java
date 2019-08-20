package ru.mdashlw.hypixel.entities;

/**
 * Rank on the network.
 */
public enum Rank {
    NORMAL("Normal"),
    VIP("VIP"),
    VIP_PLUS("VIP+"),
    MVP("MVP"),
    MVP_PLUS("MVP+"),
    SUPERSTAR("MVP++"),
    YOUTUBER("YouTuber"),
    HELPER("Helper"),
    MODERATOR("Moderator"),
    ADMIN("Administrator");

    private final String localizedName;

    Rank(final String localizedName) {
        this.localizedName = localizedName;
    }

    /**
     * Localized name.
     *
     * @return Localized name.
     */
    public String getLocalizedName() {
        return this.localizedName;
    }
}
