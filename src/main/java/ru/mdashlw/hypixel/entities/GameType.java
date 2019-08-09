package ru.mdashlw.hypixel.entities;

/**
 * Game type.
 */
public enum GameType {
    QUAKECRAFT("Quakecraft"),
    WALLS("Walls"),
    PAINTBALL("Paintball"),
    SURVIVAL_GAMES("Blitz Survival Games"),
    TNTGAMES("The TNT Games"),
    VAMPIREZ("VampireZ"),
    WALLS3("Mega Walls"),
    ARCADE("Arcade"),
    ARENA("Arena Brawl"),
    MCGO("Cops and Crims"),
    UHC("UHC Champions"),
    BATTLEGROUND("Warlords"),
    SUPER_SMASH("Smash Heroes"),
    GINGERBREAD("Turbo Kart Racers"),
    HOUSING("Housing"),
    SKYWARS("SkyWars"),
    TRUE_COMBAT("Crazy Walls"),
    SPEED_UHC("Speed UHC"),
    SKYCLASH("SkyClash"),
    LEGACY("Classic Games"),
    PROTOTYPE("Prototype"),
    BEDWARS("Bed Wars"),
    MURDER_MYSTERY("Murder Mystery"),
    BUILD_BATTLE("Build Battle"),
    DUELS("Duels"),
    PIT("The Pit"),
    SKYBLOCK("SkyBlock");

    private String localizedName;

    GameType(String localizedName) {
        this.localizedName = localizedName;
    }

    /**
     * Localized name.
     *
     * @return Localized name.
     */
    public String getLocalizedName() {
        return localizedName;
    }
}
