package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.Kit

enum class UhcKit(override val apiName: String, override val localizedName: String) : Kit {
    WORKING_TOOLS("WORKING_TOOLS", "Working Tools"),
    ARCHERY_TOOLS("ARCHERY_TOOLS", "Archery Tools"),
    ECOLOGIST("ECOLOGIST", "Ecologist"),
    LOOTER("LOOTER", "Looter"),
    MAGIC_TOOLS("MAGIC_TOOLS", "Magic Tools"),
    HORSEMAN("HORSEMAN", "Horseman"),
    FARMER("FARMER", "Farmer"),
    TRAPPER("TRAPPER", "Trapper"),
    LUNCH_BOX("LUNCH_BOX", "Lunch Box"),
    LEATHER_ARMOR("LEATHER_ARMOR", "Leather Armor");

    override fun toString(): String = localizedName
}
