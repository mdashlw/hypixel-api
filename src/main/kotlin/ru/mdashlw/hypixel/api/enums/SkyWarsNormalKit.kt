package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.Kit

enum class SkyWarsNormalKit(override val apiName: String, override val localizedName: String) : Kit {
    DEFAULT("kit_basic_solo_default", "Default"),
    ARMORER("kit_advanced_solo_armorer", "Armorer"),
    ARMORSMITH("kit_basic_solo_armorsmith", "Armorsmith"),
    BASEBALL_PLAYER("kit_basic_solo_baseball-player", "Baseball Player"),
    CANNONEER("kit_advanced_solo_cannoneer", "Cannoneer"),
    ECOLOGIST("kit_basic_solo_ecologist", "Ecologist"),
    ENCHANTER("kit_advanced_solo_enchanter", "Enchanter"),
    ENDERCHEST("kit_enderchest_solo_enderchest", "Enderchest"),
    ENDERMAN("kit_advanced_solo_enderman", "Enderman"),
    FARMER("kit_advanced_solo_farmer", "Farmer"),
    FISHERMAN("kit_basic_solo_fisherman", "Fisherman"),
    HUNTER("kit_advanced_solo_hunter", "Hunter"),
    KNIGHT("kit_advanced_solo_knight", "Knight"),
    PHARAOH("kit_basic_solo_pharaoh", "Pharaoh"),
    PYRO("kit_advanced_solo_pyro", "Pyro"),
    ROOKIE("kit_basic_solo_rookie", "Rookie"),
    SNOWMAN("kit_basic_solo_snowman", "Snowman"),
    SPELEOLOGIST("kit_basic_solo_speleologist", "Speleologist"),
    TROLL("kit_basic_solo_troll", "Troll"),
    BATGUY("kit_basic_solo_batguy", "Batguy"),
    DISCO("kit_basic_solo_disco", "Disco"),
    ENERGIX("kit_basic_solo_energix", "Energix"),
    FROG("kit_basic_solo_frog", "Frog"),
    GRENADE("kit_basic_solo_grenade", "Grenade"),
    GUARDIAN("kit_advanced_solo_guardian", "Guardian"),
    HEALER("kit_basic_solo_healer", "Healer"),
    SCOUT("kit_basic_solo_scout", "Scout"),
    PRINCESS("kit_basic_solo_princess", "Princess"),
    ENGINEER("kit_advanced_solo_engineer", "Engineer"),
    SALMON("kit_advanced_solo_salmon", "Salmon"),
    PIG_RIDER("kit_advanced_solo_pig-rider", "Pig Rider"),
    SLIME("kit_advanced_solo_slime", "Slime"),
    JESTER("kit_advanced_solo_jester", "Jester"),
    ZOOKEEPER("kit_advanced_solo_zookeeper", "Zookeeper"),
    SLOTH("kit_advanced_solo_sloth", "Sloth"),
    MAGICIAN("kit_advanced_solo_magician", "Magician"),
    END_LORD("kit_mythical_end-lord", "End Lord"),
    MONSTER_TRAINER("kit_mythical_monster-trainer", "Monster Trainer"),
    NETHER_LORD("kit_mythical_nether-lord", "Nether Lord");

    override fun toString(): String = localizedName
}
