package com.github.mdashl.hypixel.enums

import java.time.LocalDate

enum class RankedSeason(val date: String, val number: Int, val leaderboard: Map<String, Pair<Int, Int>>? = null) {

    SEASON_1(
        "4_16",
        1,
        mapOf(
            "Illumina" to (3900 to 1),
            "Shooter" to (3174 to 2),
            "enalala" to (3146 to 3),
            "Pododude" to (3113 to 4),
            "Delucaco" to (3100 to 5),
            "ashv" to (3090 to 6),
            "YoyoOfficial" to (2560 to 7),
            "Hamzah" to (2553 to 8),
            "peter_tha_gamer" to (2551 to 9),
            "nail181" to (2542 to 10)
        )
    ),
    SEASON_2(
        "5_16",
        2,
        mapOf(
            "Dctr" to (3756 to 1),
            "ReoccurisMASTER" to (3725 to 2),
            "ashv" to (3527 to 3),
            "PabllooGG" to (3318 to 4),
            "xKaos" to (3313 to 5),
            "Sarlies" to (3200 to 6),
            "Dannyxv8" to (3170 to 7),
            "Bi2f" to (3166 to 8),
            "Jsh" to (3166 to 9),
            "2Pi" to (3158 to 10)
        )
    ),
    SEASON_3(
        "6_16",
        3,
        mapOf(
            "Kqwqii" to (3357 to 1),
            "icecreamflinger" to (3341 to 2),
            "Kevos" to (3302 to 3),
            "xBasil" to (3300 to 4),
            "Technoblade" to (3166 to 5),
            "Boogei" to (3156 to 6),
            "Ashua" to (3150 to 7),
            "RaymonO_o" to (3140 to 8),
            "SalilPT" to (3130 to 9),
            "Romantic" to (3130 to 10)
        )
    ),
    SEASON_4(
        "7_16",
        4,
        mapOf(
            "Naidemoc" to (4001 to 1),
            "AaronHK" to (0 to 2),
            "Nickthemurph" to (0 to 3),
            "OnyxDragon_Line" to (0 to 4),
            "3dbluechip" to (0 to 5),
            "enalala" to (0 to 6),
            "Elyre" to (0 to 7),
            "Robbieciaron" to (0 to 8),
            "xkuuulaOuO" to (0 to 9),
            "Shadiest" to (0 to 10)
        )
    ),
    SEASON_5(
        "8_16",
        5,
        mapOf(
            "SrafeZ" to (4119 to 1),
            "xBasil" to (3534 to 2),
            "BigDaddyDrock" to (3512 to 3),
            "R1singL3gend" to (3340 to 4),
            "Defone" to (3330 to 5),
            "Psychic_Lemur" to (3306 to 6),
            "Babs" to (3304 to 7),
            "ReoccurisMASTER" to (3302 to 8),
            "Blacker_S" to (3286 to 9),
            "Gnarlak" to (3130 to 10)
        )
    ),
    SEASON_6("9_16", 6),
    SEASON_7(
        "10_16",
        7,
        mapOf(
            "TGV" to (4121 to 1),
            "Palikka" to (3500 to 2),
            "munckie" to (3306 to 3),
            "Temp33sT" to (3304 to 4),
            "Saviorr" to (3167 to 5),
            "RussianPvP" to (3167 to 6),
            "iKick" to (3162 to 7),
            "SUPPACOOL" to (3162 to 8),
            "IsoSmile" to (3154 to 9),
            "Vyrinnn" to (3152 to 10)
        )
    ),
    SEASON_8(
        "11_16",
        8,
        mapOf(
            "liliana996" to (3700 to 1),
            "xBarton" to (3362 to 2),
            "LEGENDFF" to (3350 to 3),
            "Avihay" to (3322 to 4),
            "Cild" to (3320 to 5),
            "Snuspect" to (3318 to 6),
            "Decalz" to (3316 to 8),
            "mmazza" to (3312 to 9),
            "KingRachoMacho" to (3302 to 10)
        )
    ),
    SEASON_9(
        "12_16",
        9,
        mapOf(
            "Awbo" to (3516 to 1),
            "NightColor" to (3333 to 2),
            "Max166" to (3308 to 3),
            "OoFaeganoO" to (3156 to 4),
            "TediR" to (3155 to 5),
            "iAxeHD" to (3136 to 6),
            "unz" to (3132 to 7),
            "_II_II_II_" to (3121 to 8),
            "Boogei" to (3106 to 9),
            "YRJ" to (3104 to 10)
        )
    ),
    SEASON_10(
        "1_17", 10,
        mapOf(
            "iCasual" to (4062 to 1),
            "Temp33sT" to (3904 to 2),
            "Zoomba" to (3900 to 3),
            "WorthyOrigin" to (3894 to 4),
            "Deltiq" to (3720 to 5),
            "Fono" to (3700 to 6),
            "Mowhering" to (3630 to 7),
            "MaxPigman" to (3570 to 8),
            "SkyVox_" to (3524 to 9),
            "Nyanic" to (3523 to 10)
        )
    ),
    SEASON_11(
        "2_17",
        11,
        mapOf(
            "DelayNoMore" to (3307 to 5),
            "FlaiTHEnMC" to (3152 to 6),
            "Tayber" to (3152 to 7),
            "HealthyLife" to (3130 to 8),
            "xxxSnowy" to (3130 to 9),
            "WorthyOrigin" to (3130 to 10)
        )
    ),
    SEASON_12(
        "3_17",
        12,
        mapOf(
            "Snuspect" to (3700 to 1),
            "aHarm" to (3330 to 2),
            "Yourtuber" to (3163 to 3),
            "RaaVeish" to (3158 to 4),
            "iiFlux" to (3156 to 5),
            "Optay" to (3155 to 6),
            "NoVoid" to (3152 to 7),
            "SaJaTo" to (3150 to 8),
            "Decalz" to (3133 to 9),
            "argula" to (3104 to 10)
        )
    ),
    SEASON_13(
        "4_17",
        13,
        mapOf(
            "YoyoOfficial" to (2500 to 1),
            "Mahteus" to (2499 to 2),
            "nafen" to (2498 to 3),
            "Decalz" to (2497 to 4),
            "YGP" to (2496 to 5),
            "armknock" to (2495 to 6),
            "Snuspect" to (2494 to 7),
            "RaaVeish" to (2493 to 8),
            "iAxeHD" to (2492 to 9),
            "Kamilczak7" to (2491 to 10)
        )
    ),
    SEASON_14(
        "5_17",
        14,
        mapOf(
            "Childish" to (3621 to 1),
            "Vexerr" to (3470 to 2),
            "liliana996" to (3130 to 3),
            "Optay" to (3126 to 4),
            "Snuspect" to (3110 to 5),
            "Decalz" to (3107 to 6),
            "ToggleShift" to (3103 to 7),
            "NightColor" to (3077 to 8),
            "Mario3573" to (3073 to 9),
            "Yourtuber" to (3057 to 10)
        )
    ),
    SEASON_15(
        "6_17",
        15,
        mapOf(
            "Vexerr" to (0 to 1)
        )
    ),
    SEASON_16(
        "7_17",
        16,
        mapOf(
            "Childish" to (9040 to 1),
            "Wolvax" to (8300 to 2),
            "Palikka" to (7012 to 3),
            "Core" to (6120 to 4),
            "Kamilczak7" to (5693 to 5),
            "Hydoxous" to (5606 to 6),
            "Auror_" to (5505 to 7),
            "Vexerr" to (5333 to 8),
            "Qwikkspell" to (5294 to 9),
            "CosQ" to (5205 to 10)
        )
    ),
    SEASON_17(
        "8_17",
        17,
        mapOf(
            "iAxeHD" to (3063 to 1),
            "RKY" to (3057 to 2),
            "katjahsiung" to (3042 to 3),
            "FreeCuba" to (3015 to 4),
            "xJackalope" to (3007 to 5),
            "Hype" to (3006 to 6),
            "Uselessness" to (3005 to 7),
            "Zombiez" to (3004 to 8),
            "ComplexData" to (3001 to 9),
            "SpectroPlayer" to (3001 to 10)
        )
    ),
    SEASON_18(
        "9_17",
        18,
        mapOf(
            "Wolvax" to (3130 to 1),
            "nickypo" to (3105 to 2),
            "ucz" to (3097 to 3),
            "Hype" to (3093 to 4),
            "YouBot" to (3091 to 5),
            "SpectroPlayer" to (3086 to 6),
            "Vexerr" to (3065 to 7),
            "Lenify" to (3062 to 8),
            "Seren" to (3059 to 9),
            "Hydoxous" to (3049 to 10)
        )
    ),
    SEASON_19(
        "10_17",
        19,
        mapOf(
            "KBlazii" to (3176 to 1),
            "yungmase" to (3161 to 2),
            "Kausly" to (3119 to 3),
            "Wolvax" to (3101 to 4),
            "Cerni" to (3081 to 5),
            "Nickthemurph" to (3044 to 6),
            "Snuspect" to (3039 to 7),
            "abitha" to (3038 to 8),
            "Zombiez" to (3020 to 9),
            "Zenection" to (3018 to 10)
        )
    ),
    SEASON_20(
        "11_17",
        20,
        mapOf(
            "Cerni" to (3096 to 1),
            "Childish" to (3041 to 2),
            "CosQ" to (3033 to 3),
            "Wolvax" to (3032 to 4),
            "Zombiez" to (3028 to 5),
            "Nickthemurph" to (3020 to 6),
            "Hydoxous" to (3019 to 7),
            "Microchip" to (3013 to 8),
            "Onyc" to (3013 to 9),
            "Fzd" to (3004 to 10)
        )
    ),
    SEASON_21(
        "12_17",
        21,
        mapOf(
            "YouBot" to (3069 to 1),
            "Childish" to (3060 to 2),
            "Conquette" to (3060 to 3),
            "gdo" to (3057 to 4),
            "Julian" to (3051 to 5),
            "NoHaxJustBaByy" to (3051 to 6),
            "Cerni" to (3042 to 7),
            "Auror_" to (3006 to 8),
            "jewz" to (2971 to 9),
            "WopHopOnYouTube" to (2872 to 10)
        )
    ),
    SEASON_22(
        "1_18",
        22,
        mapOf(
            "NoHaxJustBaByy" to (3124 to 1),
            "CosQ" to (3102 to 2),
            "Pelter" to (3071 to 3),
            "L1E" to (3058 to 4),
            "Conventionality" to (3028 to 5),
            "iAxeHD" to (3018 to 6),
            "Hydoxous" to (3015 to 7),
            "Zombiez" to (3013 to 8),
            "Sybill" to (3010 to 9),
            "liliana996" to (3001 to 10)
        )
    ),
    SEASON_23(
        "2_18",
        23,
        mapOf(
            "SpectroPlayer" to (3138 to 1),
            "WHYWIN" to (3121 to 2),
            "Conventionality" to (3119 to 3),
            "NiSsePiP" to (3102 to 4),
            "HypickelDragon" to (3101 to 5),
            "Optay" to (3081 to 6),
            "PeterMC" to (3078 to 7),
            "Vladiss" to (3071 to 8),
            "Sackboy_Clank5" to (3068 to 9),
            "CosQ" to (3027 to 10)
        )
    ),
    SEASON_24(
        "3_18",
        24,
        mapOf(
            "sayanz" to (3228 to 1),
            "RankedLB" to (3205 to 2),
            "SupremeUK" to (3178 to 3),
            "nejii" to (3168 to 4),
            "Bequite" to (3168 to 5),
            "Fohgi" to (3162 to 6),
            "ilydis" to (3160 to 7),
            "Baylow" to (3155 to 8),
            "Sackboy_Clank5" to (3112 to 10),
            "Zombiez" to (3151 to 10)
        )
    ),
    SEASON_25(
        "4_18",
        25,
        mapOf(
            "Lorieo" to (3216 to 1),
            "Vayji" to (3170 to 2),
            "HeartTrails" to (3164 to 3),
            "KingCbg" to (3120 to 4),
            "Exotico" to (3020 to 5),
            "chef_wzt" to (3005 to 6),
            "GetRespected" to (3002 to 7),
            "Childish" to (2982 to 8),
            "geniy" to (2774 to 9),
            "Raygle" to (2711 to 10),
            "Cloudz" to (2706 to 10),
            "Daveful" to (2664 to 10)
        )
    ),
    SEASON_26(
        "5_18",
        26,
        mapOf(
            "Trushi" to (3143 to 1),
            "Raynowing" to (3101 to 2),
            "Auror_" to (3020 to 3),
            "IPGGG" to (3018 to 4),
            "Sackboy_Clank5" to (2941 to 5),
            "Cyld" to (2901 to 6),
            "WorthyOrigin" to (2806 to 7),
            "BlatantReech" to (2781 to 8),
            "Ratputin" to (2775 to 9),
            "ellikahlol" to (2714 to 10),
            "irkt" to (2661 to 10),
            "EzKidLIFELONG" to (2507 to 10)
        )
    ),
    SEASON_27(
        "6_18",
        27,
        mapOf(
            "8q" to (3401 to 1),
            "Fohgi" to (3309 to 2),
            "FLO5TEN" to (3302 to 3),
            "ProPvper48" to (3300 to 4),
            "Wolvervine" to (3280 to 5),
            "PapaMawr" to (3270 to 6),
            "FreeCuba" to (3266 to 7),
            "Latchi" to (3241 to 8),
            "jordanisthabest" to (3240 to 9),
            "eZkidReln" to (3230 to 10),
            "FO0T" to (3005 to 10)
        )
    ),
    SEASON_28(
        "7_18",
        28,
        mapOf(
            "deathno2" to (4006 to 1),
            "mineccn" to (3722 to 2),
            "reflectors" to (3404 to 3),
            "MasterDol" to (3354 to 4),
            "SnowFlower" to (3352 to 5),
            "GoodestEnglish" to (3338 to 6),
            "geniy" to (3316 to 7),
            "MinorEvent" to (3311 to 8),
            "WHYWIN" to (3274 to 9),
            "Raygle" to (3227 to 10)
        )
    ),
    SEASON_29(
        "8_18",
        29,
        mapOf(
            "NoHaxJustBabyy" to (3523 to 1),
            "Baylow14" to (3512 to 2),
            "ToxicChan" to (3459 to 3),
            "CheckingStats" to (3451 to 4),
            "eZkidReln" to (3449 to 5),
            "blyar" to (3409 to 6),
            "Chixs" to (3403 to 7),
            "irkt" to (3352 to 8),
            "SpectroPlayer" to (3338 to 9),
            "Childish" to (3304 to 9),
            "DrqgonRider" to (3031 to 10)
        )
    ),
    SEASON_30(
        "9_18",
        30,
        mapOf(
            "Ratputin" to (3561 to 1),
            "Guala" to (3418 to 2),
            "reflectors" to (3415 to 3),
            "ToxicChan" to (3411 to 4),
            "eZkidK3NNY" to (3402 to 5),
            "chef_wzt" to (3360 to 6),
            "steww" to (3266 to 7),
            "HetIsAAPIE" to (3251 to 8),
            "irkt" to (3194 to 9),
            "Auror_" to (3009 to 10),
            "5fv" to (2935 to 10),
            "DrqgonRider" to (2825 to 10),
            "FearMyTear" to (2765 to 10),
            "Fohgi" to (2755 to 10)
        )
    ),
    SEASON_31(
        "10_18",
        31,
        mapOf(
            "dqves" to (6734 to 1),
            "oZypex" to (4390 to 4),
            "ELLAPVPGOD69" to (4339 to 5),
            "ilydis" to (4206 to 6),
            "Visiti" to (3820 to 7),
            "Baylows" to (3719 to 8),
            "Im_Gay_Master" to (3684 to 9),
            "Guala" to (3656 to 10),
            "Rush" to (3482 to 10)
        )
    ),
    SEASON_32(
        "11_18",
        32,
        mapOf(
            "PapaMawr" to (3531 to 1),
            "ilySamuel" to (3496 to 2),
            "Twincles" to (3491 to 3),
            "Feale" to (3490 to 4),
            "Extremesy" to (3382 to 6),
            "Auror_" to (3363 to 7),
            "Temp0" to (3353 to 8),
            "FreeCuba" to (3308 to 9),
            "blyar" to (3350 to 10),
            "telephaty" to (3017 to 10)
        )
    ),
    SEASON_33(
        "12_18",
        33,
        mapOf(
            "BaoShingLao" to (3886 to 1),
            "Bazt" to (3861 to 2),
            "udorable" to (3796 to 3),
            "Reoccur" to (3765 to 4),
            "RankedLB" to (3760 to 5),
            "Tayshawn" to (3759 to 6),
            "Mouses" to (3740 to 7),
            "HetIsAAPIE" to (3733 to 8),
            "DeAmeer" to (3679 to 9),
            "EzKidLIFELONG" to (3628 to 10),
            "Lenify" to (3602 to 10)
        )
    ),
    SEASON_34("1_19", 34),
    SEASON_35("2_19", 35),
    SEASON_36("3_19", 36),
    SEASON_37("4_19", 37),
    SEASON_38("5_19", 38),
    SEASON_39("6_19", 39),
    SEASON_40("7_19", 40),
    SEASON_41("8_19", 41),
    SEASON_42("9_19", 42),
    SEASON_43("10_19", 43),
    SEASON_44("11_19", 44),
    SEASON_45("12_19", 45),
    SEASON_46("1_20", 46),
    SEASON_47("2_20", 47),
    SEASON_48("3_20", 48),
    SEASON_49("4_20", 49),
    SEASON_50("5_20", 50);

    val rewards: Map<RankedDivision, List<RankedReward>> by lazy {
        mapOf(
            RankedDivision.MASTERS to listOf(
                RankedReward.DRAGON_RIDER,
                RankedDivision.MASTERS.rewards[number % 2]
            ),
            RankedDivision.DIAMOND to listOf(
                RankedReward.MAGIC_BOX,
                RankedDivision.DIAMOND.rewards[number % 3],
                RankedDivision.DIAMOND.rewards[number % 4 + 3]
            ),
            RankedDivision.GOLD to listOf(
                RankedDivision.GOLD.rewards[number % 3]
            )
        )
    }

    companion object {

        val CURRENT_SEASON: RankedSeason by lazy {
            val date = LocalDate.now()

            get("${date.monthValue}_${date.year.toString().drop(2)}")
        }

        operator fun get(date: String): RankedSeason = values().find { it.date == date }!!

    }

}
