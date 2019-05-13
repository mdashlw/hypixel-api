package ru.mdashlw.hypixel.api.enums

enum class CosmeticType(val apiName: String, val localizedName: String) {
    PROJECTILE_TRAIL("projectiletrail", "Projectile Trail"),
    CAGE("cage", "Cage"),
    VICTORY_DANCE("victorydance", "Victory Dance"),
    KILL_EFFECT("killeffect", "Kill Effect"),
    DEATH_CRY("deathcry", "Death Cry"),
    BALLOON("balloon", "Balloon"),
    KILL_MESSAGES("killmessages", "Kill Messages"),
    SPRAYS("sprays", "Sprays");

    override fun toString(): String = localizedName
}
