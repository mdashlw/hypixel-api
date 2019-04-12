package ru.mdashlw.hypixel.enums

// Order is important
enum class RankedReward(
    val division: RankedDivision,
    val apiName: String,
    val localizedName: String
) {
    FINAL_SMASH(RankedDivision.MASTERS, "killeffect_final_smash", "Final Smash"),
    HEAD_ROCKET(RankedDivision.MASTERS, "killeffect_head_rocket", "Head Rocket"),
    DRAGON_RIDER(RankedDivision.MASTERS, "victorydance_dragon_rider", "Dragon Rider"),

    HEART_EXPLOSION(RankedDivision.DIAMOND, "killeffect_heart_explosion", "Heart Explosion"),
    GREEN_STAR(RankedDivision.DIAMOND, "projectiletrail_green_star", "Green Star"),
    NOTES(RankedDivision.DIAMOND, "projectiletrail_notes", "Notes"),
    BAZINGA(RankedDivision.DIAMOND, "deathcry_bazinga", "Bazinga"),
    GRUMPY_VILLAGER(RankedDivision.DIAMOND, "deathcry_grumpy_villager", "Grumpy Villager"),
    MONSTER_BURP(RankedDivision.DIAMOND, "deathcry_monster_burp", "Monster Burp"),
    SAD_PUPPY(RankedDivision.DIAMOND, "deathcry_sad_puppy", "Sad Puppy"),
    MAGIC_BOX(RankedDivision.DIAMOND, "cage_magic-box-cage", "Magic Box"),

    GUARDIANS(RankedDivision.GOLD, "victorydance_guardians", "Guardians"),
    BLOOD_EXPLOSION(RankedDivision.GOLD, "killeffect_blood_explosion", "Blood Explosion"),
    HEARTS(RankedDivision.GOLD, "projectiletrail_hearts", "Hearts");

    override fun toString(): String = localizedName
}
