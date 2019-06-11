package ru.mdashlw.hypixel.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Response
import ru.mdashlw.hypixel.api.entities.Guild
import ru.mdashlw.hypixel.api.entities.Key
import ru.mdashlw.hypixel.api.entities.Session
import ru.mdashlw.hypixel.api.entities.player.HypixelPlayer
import ru.mdashlw.hypixel.api.exceptions.HypixelApiException
import ru.mdashlw.hypixel.api.exceptions.HypixelApiThrottleException
import ru.mdashlw.hypixel.api.reply.Reply
import ru.mdashlw.hypixel.api.reply.impl.GuildReply
import ru.mdashlw.hypixel.api.reply.impl.KeyReply
import ru.mdashlw.hypixel.api.reply.impl.PlayerReply
import ru.mdashlw.hypixel.api.reply.impl.SessionReply
import ru.mdashlw.hypixel.api.util.newCall
import kotlin.reflect.KClass

object HypixelApi {
    const val BASE_URL = "https://api.hypixel.net/"

    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val jackson: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    lateinit var keys: List<String>
    lateinit var outputMode: OutputMode

    fun setup(keys: List<String>, outputMode: OutputMode = HypixelApi.OutputMode.RAW) {
        this.keys = keys
        this.outputMode = outputMode
    }

    fun setup(key: String, outputMode: OutputMode = HypixelApi.OutputMode.RAW) {
        setup(listOf(key), outputMode)
    }

    fun retrievePlayerByUuid(uuid: String): HypixelPlayer? = get<PlayerReply, HypixelPlayer>("player", "uuid" to uuid)

    fun retrievePlayerByName(name: String): HypixelPlayer? = get<PlayerReply, HypixelPlayer>("player", "name" to name)

    fun retrieveSessionByUuid(uuid: String): Session? = get<SessionReply, Session>("session", "uuid" to uuid)

    fun retrieveGuildByName(name: String): Guild? = get<GuildReply, Guild>("guild", "name" to name)

    fun retrieveGuildByPlayer(uuid: String): Guild? = get<GuildReply, Guild>("guild", "player" to uuid)

    fun retrieveKey(key: String): Key? = get<KeyReply, Key>("key", key = key)

    inline fun <reified R : Reply<T>, T> get(
        endpoint: String,
        parameter: Pair<String, Any>? = null,
        key: String = keys.random()
    ): T? = get(R::class, endpoint, parameter, key)

    fun <R : Reply<T>, T> get(
        replyClass: KClass<R>,
        endpoint: String,
        parameter: Pair<String, Any>?,
        key: String
    ): T? {
        var url = "$BASE_URL$endpoint?key=$key"

        parameter?.let { (key, value) ->
            url += "&$key=$value"
        }

        val response = okHttpClient.newCall(url).takeIf(Response::isSuccessful) ?: return null
        val body = response.body() ?: return null
        val reply = body.use { jackson.readValue(body.string(), replyClass.java) }

        reply.run {
            if (throttle) {
                throw HypixelApiThrottleException()
            } else if (!success) {
                throw HypixelApiException(cause!!)
            }

            return element
        }
    }

    enum class OutputMode {
        RAW,
        MARKDOWN,
        COLORIZED;
    }
}
