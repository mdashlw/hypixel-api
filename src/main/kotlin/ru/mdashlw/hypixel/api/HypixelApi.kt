package ru.mdashlw.hypixel.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import ru.mdashlw.hypixel.api.entities.Guild
import ru.mdashlw.hypixel.api.entities.Key
import ru.mdashlw.hypixel.api.entities.Session
import ru.mdashlw.hypixel.api.entities.player.HypixelPlayer
import ru.mdashlw.hypixel.api.exceptions.HypixelApiException
import ru.mdashlw.hypixel.api.exceptions.HypixelApiThrottleException
import ru.mdashlw.hypixel.api.reply.Reply
import ru.mdashlw.hypixel.api.util.newCall
import kotlin.reflect.KClass

object HypixelApi {
    const val BASE_URL = "https://api.hypixel.net/"

    internal val jackson: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    internal val okHttpClient: OkHttpClient = OkHttpClient()

    lateinit var keys: List<String>
    lateinit var outputMode: OutputMode

    fun setup(keys: List<String>, outputMode: OutputMode = HypixelApi.OutputMode.RAW) {
        this.keys = keys
        this.outputMode = outputMode
    }

    fun setup(key: String, outputMode: OutputMode = HypixelApi.OutputMode.RAW) {
        setup(listOf(key), outputMode)
    }

    fun getPlayerByUuid(uuid: String): HypixelPlayer? = get("player", "uuid" to uuid)

    fun getPlayerByName(name: String): HypixelPlayer? = get("player", "name" to name)

    fun getSessionByUuid(uuid: String): Session? = get("session", "uuid" to uuid)

    fun getGuildByName(name: String): Guild? = get("guild", "name" to name)

    fun getGuildByPlayer(uuid: String): Guild? = get("guild", "player" to uuid)

    fun getKey(key: String): Key? = get("key", key = key)

    inline fun <reified R : Reply<T>, T> get(
        endpoint: String,
        parameter: Pair<String, Any>? = null,
        key: String = keys.random()
    ): T? = get(R::class, endpoint, parameter, key)

    @PublishedApi
    internal fun <R : Reply<T>, T> get(
        replyClass: KClass<R>,
        endpoint: String,
        parameter: Pair<String, Any>?,
        key: String
    ): T? {
        var url = "$BASE_URL$endpoint?key=$key"

        parameter?.let { (key, value) ->
            url += "&$key=$value"
        }

        val response = okHttpClient.newCall(url)
        val reply = jackson.readValue(response, replyClass.java)

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
