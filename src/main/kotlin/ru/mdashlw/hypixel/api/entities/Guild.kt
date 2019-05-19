package ru.mdashlw.hypixel.api.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import ru.mdashlw.hypixel.api.HypixelApi
import ru.mdashlw.hypixel.api.util.GuildLevelingUtil

data class Guild(
    val name: String,
    val coins: Int,
    val members: List<Member>,
    val joinable: Boolean = false,
    val publiclyListed: Boolean = false,
    val tag: String? = null,
    val achievements: Map<String, Int>? = null,
    val exp: Long,
    val legacyRanking: Int = 0,
    val description: String? = null
) {
    @JsonIgnore
    val level: Int = GuildLevelingUtil.getLevel(exp)

    @JsonIgnore
    val planckeURL: String = "https://plancke.io/hypixel/guild/name/${name.replace(" ", "%20")}"

    @JsonIgnore
    val formattedDisplayname: String =
        when (HypixelApi.outputMode) {
            // TODO Make colorized seperated
            HypixelApi.OutputMode.RAW, HypixelApi.OutputMode.COLORIZED ->
                tag?.let { "[$it] $name" } ?: name
            HypixelApi.OutputMode.MARKDOWN ->
                "[${tag?.let { "[$it] $name" } ?: name}]($planckeURL)"
        }

    data class Member(
        val uuid: String,
        val rank: String
    )
}
