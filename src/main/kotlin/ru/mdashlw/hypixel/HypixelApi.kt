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
import ru.mdashlw.hypixel.reply.GuildReply
import ru.mdashlw.hypixel.reply.PlayerReply
import ru.mdashlw.hypixel.reply.Reply
import ru.mdashlw.hypixel.reply.SessionReply
import ru.mdashlw.hypixel.util.call
import java.util.*
import kotlin.reflect.KClass

object HypixelApi {
    private const val BASE_URL = "https://api.hypixel.net/"

    @JvmField
    val OBJECT_MAPPER: ObjectMapper =
        jacksonObjectMapper()
            .apply {
                setConfig(deserializationConfig.withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES))
            }
    @JvmField
    val OK_HTTP_CLIENT: OkHttpClient = OkHttpClient()

    var mode: Mode = Mode.UNCOLORIZED
    lateinit var apiKey: UUID

    fun getPlayerByUuid(uuid: String): HypixelPlayer? =
        get(PlayerReply::class, "player", "uuid" to uuid)

    fun getPlayerByName(name: String): HypixelPlayer? =
        get(PlayerReply::class, "player", "name" to name)

    fun getSessionByUuid(uuid: String): Session? =
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

        val response = OK_HTTP_CLIENT.call(url)
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

    enum class Mode {
        UNCOLORIZED,
        COLORIZED;
    }
}
