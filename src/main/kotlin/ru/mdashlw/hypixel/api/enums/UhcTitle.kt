package ru.mdashlw.hypixel.api.enums

enum class UhcTitle(val star: Int, val scoreNeeded: Int, val localizedName: String) {
    RECRUIT(1, 0, "Recruit"),
    INITIATE(2, 10, "Initiate"),
    SOLDIER(3, 60, "Soldier"),
    SERGEANT(4, 210, "Sergeant"),
    KNIGHT(5, 460, "Knight"),
    CAPTAIN(6, 960, "Captain"),
    CENTURION(7, 1710, "Centurion"),
    GLADIATOR(8, 2710, "Gladiator"),
    WARLORD(9, 5210, "Warlord"),
    CHAMPION(10, 10210, "Champion"),
    CHAMPION_PLUS(11, 13210, "Champion"),
    CHAMPION_PLUS_PLUS(12, 16210, "Champion"),
    CHAMPION_PLUS_PLUS_PLUS(13, 19210, "Champion"),
    CHAMPION_PLUS_PLUS_PLUS_PLUS(14, 22210, "Champion"),
    HIGH_CHAMPION(15, 25210, "High Champion");

    override fun toString(): String = localizedName
}
