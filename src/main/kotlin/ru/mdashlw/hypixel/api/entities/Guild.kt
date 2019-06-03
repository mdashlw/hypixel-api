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
    val level: Int
        @JsonIgnore
        get() = GuildLevelingUtil.getLevel(exp)

    val planckeUrl: String
        @JsonIgnore
        get() = "https://plancke.io/hypixel/guild/name/${name.replace(" ", "%20")}"

    val formattedDisplayname: String
        @JsonIgnore
        get() = when (HypixelApi.outputMode) {
            // TODO Adapt to colorized output mode
            HypixelApi.OutputMode.RAW, HypixelApi.OutputMode.COLORIZED ->
                tag?.let { "[$it] $name" } ?: name
            HypixelApi.OutputMode.MARKDOWN ->
                "[${tag?.let { "[$it] $name" } ?: name}]($planckeUrl)"
        }

    data class Member(
        val uuid: String,
        val rank: String
    )
}
