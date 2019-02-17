package com.github.mdashl.hypixel

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.mdashl.hypixel.elements.Guild
import com.github.mdashl.hypixel.elements.Session
import com.github.mdashl.hypixel.elements.player.HypixelPlayer
import com.github.mdashl.hypixel.exceptions.HypixelAPIException
import com.github.mdashl.hypixel.exceptions.HypixelAPIThrottleException
import com.github.mdashl.hypixel.extensions.call
import com.github.mdashl.hypixel.reply.GuildReply
import com.github.mdashl.hypixel.reply.PlayerReply
import com.github.mdashl.hypixel.reply.Reply
import com.github.mdashl.hypixel.reply.SessionReply
import okhttp3.OkHttpClient
import java.util.*
import kotlin.reflect.KClass

object HypixelAPI {

    private const val BASE_URL = "https://api.hypixel.net/"

    private val OBJECT_MAPPER: ObjectMapper = jacksonObjectMapper()
    private val OK_HTTP_CLIENT: OkHttpClient = OkHttpClient()

    lateinit var apiKey: UUID

    init {
        OBJECT_MAPPER.setConfig(
            OBJECT_MAPPER.deserializationConfig
                .withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        )
    }

    fun getPlayerByUUID(uuid: String): HypixelPlayer? = get(PlayerReply::class, "player", "uuid" to uuid)

    fun getPlayerByName(name: String): HypixelPlayer? = get(PlayerReply::class, "player", "name" to name)

    fun getSessionByUUID(uuid: String): Session? = get(SessionReply::class, "session", "uuid" to uuid)

    fun getGuildByName(name: String): Guild? = get(GuildReply::class, "guild", "name" to name)

    fun getGuildByPlayer(uuid: String): Guild? = get(GuildReply::class, "guild", "player" to uuid)

    fun <R : Reply<T>, T> get(clazz: KClass<R>, endpoint: String, parameter: Pair<String, Any>? = null): T? {
        var url = "$BASE_URL$endpoint?key=$apiKey"

        parameter?.let { (key, value) ->
            url += "&$key=$value"
        }

        val response = OK_HTTP_CLIENT.call(url)
        val reply = OBJECT_MAPPER.readValue(response, clazz.java)

        reply.run {
            if (throttle) {
                throw HypixelAPIThrottleException()
            } else if (!success) {
                throw HypixelAPIException(cause!!)
            }

            return element
        }
    }

}
