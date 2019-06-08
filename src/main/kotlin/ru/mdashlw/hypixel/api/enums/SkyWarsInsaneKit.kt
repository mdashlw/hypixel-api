package ru.mdashlw.hypixel.api.enums

import ru.mdashlw.hypixel.api.interfaces.kits.SkyWarsKit

enum class SkyWarsInsaneKit(override val apiName: String, override val localizedName: String) : SkyWarsKit {
    DEFAULT("kit_mining_team_default", "Default"),
    ARMORER("kit_defending_team_armorer", "Armorer"),
    ARMORSMITH("kit_supporting_team_armorsmith", "Armorsmith"),
    BASEBALL_PLAYER("kit_defending_team_baseball-player", "Baseball Player"),
    CANNONEER("kit_mining_team_cannoneer", "Cannoneer"),
    ECOLOGIST("kit_supporting_team_ecologist", "Ecologist"),
    ENCHANTER("kit_supporting_team_enchanter", "Enchanter"),
    ENDERMAN("kit_attacking_team_enderman", "Enderman"),
    GUARDIAN("kit_defending_team_guardian", "Guardian"),
    HEALER("kit_supporting_team_healer", "Healer"),
    HUNTER("kit_attacking_team_hunter", "Hunter"),
    KNIGHT("kit_attacking_team_knight", "Knight"),
    PHARAOH("kit_supporting_team_pharaoh", "Pharaoh"),
    ROOKIE("kit_supporting_team_rookie", "Pro"),
    SCOUT("kit_attacking_team_scout", "Scout"),
    SNOWMAN("kit_attacking_team_snowman", "Snowman"),
    SPELEOLOGIST("kit_mining_team_speleologist", "Speleologist"),
    BATGUY("kit_defending_team_batguy", "Batguy"),
    DISCO("kit_defending_team_disco", "Disco"),
    ENERGIX("kit_attacking_team_energix", "Energix"),
    FROG("kit_defending_team_frog", "Frog"),
    GRENADE("kit_attacking_team_grenade", "Grenade"),
    ENGINEER("kit_attacking_team_engineer", "Engineer"),
    PIG_RIDER("kit_attacking_team_pig-rider", "Pig Rider"),
    SALMON("kit_attacking_team_salmon", "Salmon"),
    SLIME("kit_attacking_team_slime", "Slime"),
    JESTER("kit_attacking_team_jester", "Jester"),
    ZOOKEEPER("kit_supporting_team_zookeeper", "Zookeeper"),
    SLOTH("kit_attacking_team_sloth", "Sloth"),
    MAGICIAN("kit_attacking_team_magician", "Magician"),
    ENDERCHEST("kit_enderchest_team_enderchest", "Enderchest"),
    FARMER("kit_defending_team_farmer", "Farmer"),
    FISHERMAN("kit_attacking_team_fisherman", "Fisherman"),
    PRINCESS("kit_supporting_team_princess", "Princess"),
    PYRO("kit_supporting_team_pyro", "Pyro"),
    TROLL("kit_supporting_team_troll", "Troll"),
    END_LORD("kit_mythical_end-lord", "End Lord"),
    MONSTER_TRAINER("kit_mythical_monster-trainer", "Monster Trainer"),
    NETHER_LORD("kit_mythical_nether-lord", "Nether Lord");

    override fun toString(): String = localizedName
}
