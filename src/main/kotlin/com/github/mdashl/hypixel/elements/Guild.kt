package com.github.mdashl.hypixel.elements

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.mdashl.hypixel.util.GuildLevelingUtil

data class Guild(
    val name: String,
    val coins: Int,
    val members: List<Member>,
    val joinable: Boolean = false,
    val publiclyListed: Boolean = false,
    val tag: String? = null,
    val achievements: Achievements? = null,
    val exp: Long,
    val legacyRanking: Int = 0,
    val description: String? = null
) {
    @JsonIgnore
    val level: Int = GuildLevelingUtil.getLevel(exp)

    @JsonIgnore
    val planckeURL: String = "https://plancke.io/hypixel/guild/name/${name.replace(" ", "%20")}"

    @JsonIgnore
    val formattedDisplayname: String = "[${tag?.let { "[$it] $name" } ?: name}]($planckeURL)"

    data class Member(
        val uuid: String,
        val rank: String
    )

    data class Achievements(
        @JsonProperty("WINNERS") val winners: Int = 0,
        @JsonProperty("EXPERIENCE_KINGS") val experienceKings: Int = 0,
        @JsonProperty("ONLINE_PLAYERS") val onlinePlayers: Int = 0
    )
}
