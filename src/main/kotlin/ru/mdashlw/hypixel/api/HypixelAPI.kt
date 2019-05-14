package ru.mdashlw.hypixel.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import ru.mdashlw.hypixel.api.elements.Guild
import ru.mdashlw.hypixel.api.elements.Session
import ru.mdashlw.hypixel.api.elements.player.HypixelPlayer
import ru.mdashlw.hypixel.api.exceptions.HypixelApiException
import ru.mdashlw.hypixel.api.exceptions.HypixelApiThrottleException
import ru.mdashlw.hypixel.api.reply.Reply
import ru.mdashlw.hypixel.api.util.newCall
import java.util.*
import kotlin.reflect.KClass

object HypixelAPI {
    private const val BASE_URL = "https://api.hypixel.net/"

    internal val jackson: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    internal val okHttpClient: OkHttpClient = OkHttpClient()

    var outputMode: OutputMode = HypixelAPI.OutputMode.RAW
    lateinit var apiKey: UUID

    fun getPlayerByUUID(uuid: String): HypixelPlayer? = get("player", "uuid" to uuid)

    fun getPlayerByName(name: String): HypixelPlayer? = get("player", "name" to name)

    fun getSessionByUUID(uuid: String): Session? = get("session", "uuid" to uuid)

    fun getGuildByName(name: String): Guild? = get("guild", "name" to name)

    fun getGuildByPlayer(uuid: String): Guild? = get("guild", "player" to uuid)

    fun <R : Reply<T>, T> get(replyClass: KClass<R>, endpoint: String, parameter: Pair<String, Any>? = null): T? {
        var url = "$BASE_URL$endpoint?key=$apiKey"

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

    inline fun <reified R : Reply<T>, T> get(endpoint: String, parameter: Pair<String, Any>? = null): T? =
        get(R::class, endpoint, parameter)

    enum class OutputMode {
        RAW,
        MARKDOWN,
        COLORIZED;
    }
}
