package ru.mdashlw.hypixel

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import ru.mdashlw.hypixel.elements.Guild
import ru.mdashlw.hypixel.elements.Session
import ru.mdashlw.hypixel.elements.player.HypixelPlayer
import ru.mdashlw.hypixel.exceptions.HypixelApiException
import ru.mdashlw.hypixel.exceptions.HypixelApiThrottleException
import ru.mdashlw.hypixel.reply.Reply
import ru.mdashlw.hypixel.reply.impl.GuildReply
import ru.mdashlw.hypixel.reply.impl.PlayerReply
import ru.mdashlw.hypixel.reply.impl.SessionReply
import ru.mdashlw.hypixel.util.newCall
import java.util.*
import kotlin.reflect.KClass

object HypixelAPI {
    private const val BASE_URL = "https://api.hypixel.net/"

    @JvmField
    val OBJECT_MAPPER: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    @JvmField
    val OK_HTTP_CLIENT: OkHttpClient = OkHttpClient()

    var outputMode: OutputMode = OutputMode.RAW
    lateinit var apiKey: UUID

    fun getPlayerByUUID(uuid: String): HypixelPlayer? =
        get(PlayerReply::class, "player", "uuid" to uuid)

    fun getPlayerByName(name: String): HypixelPlayer? =
        get(PlayerReply::class, "player", "name" to name)

    fun getSessionByUUID(uuid: String): Session? =
        get(SessionReply::class, "session", "uuid" to uuid)

    fun getGuildByName(name: String): Guild? =
        get(GuildReply::class, "guild", "name" to name)

    fun getGuildByPlayer(uuid: String): Guild? =
        get(GuildReply::class, "guild", "player" to uuid)

    fun <R : Reply<T>, T> get(clazz: KClass<R>, endpoint: String, parameter: Pair<String, Any>? = null): T? {
        var url = "$BASE_URL$endpoint?key=$apiKey"

        parameter?.let { (key, value) ->
            url += "&$key=$value"
        }

        val response = OK_HTTP_CLIENT.newCall(url)
        val reply = OBJECT_MAPPER.readValue(response, clazz.java)

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
