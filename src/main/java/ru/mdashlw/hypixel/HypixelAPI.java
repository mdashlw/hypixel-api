package ru.mdashlw.hypixel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import ru.mdashlw.hypixel.entities.Friendship;
import ru.mdashlw.hypixel.entities.Guild;
import ru.mdashlw.hypixel.entities.Key;
import ru.mdashlw.hypixel.entities.Session;
import ru.mdashlw.hypixel.entities.player.HypixelPlayer;
import ru.mdashlw.hypixel.exceptions.HypixelApiException;
import ru.mdashlw.hypixel.exceptions.HypixelApiThrottleException;
import ru.mdashlw.hypixel.reply.Reply;
import ru.mdashlw.hypixel.reply.impl.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Hypixel API.
 */
public final class HypixelAPI {
    /**
     * Base URL for the Hypixel API.
     */
    public static final String BASE_URL = "https://api.hypixel.net/";

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private String key;

    /**
     * @param key API key.
     */
    public HypixelAPI(final String key) {
        this(key, new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build());
    }

    /**
     * @param key          API key.
     * @param okHttpClient OkHttpClient instance.
     */
    public HypixelAPI(final String key, final OkHttpClient okHttpClient) {
        this(key, okHttpClient, new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    /**
     * @param key          API key.
     * @param okHttpClient OkHttpClient instance.
     * @param objectMapper ObjectMapper instance.
     */
    public HypixelAPI(final String key, final OkHttpClient okHttpClient, final ObjectMapper objectMapper) {
        this.key = key;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieve player information.
     *
     * @param name Player name.
     * @return Completable future of HypixelPlayer.
     */
    public CompletableFuture<HypixelPlayer> retrievePlayerByName(final String name) {
        return this.retrieve(PlayerReply.class, "player", "name", name);
    }

    /**
     * Retrieve player information.
     *
     * @param uuid Dashed/undashed player UUID.
     * @return Completable future of HypixelPlayer.
     */
    public CompletableFuture<HypixelPlayer> retrievePlayerByUuid(final String uuid) {
        return this.retrieve(PlayerReply.class, "player", "uuid", uuid);
    }

    /**
     * Retrieve guild information.
     *
     * @param name Guild name.
     * @return Completable future of Guild.
     */
    public CompletableFuture<Guild> retrieveGuildByName(final String name) {
        return this.retrieve(GuildReply.class, "guild", "name", name);
    }

    /**
     * Retrieve guild information.
     *
     * @param player Dashed/undashed player UUID.
     * @return Completable future of Guild.
     */
    public CompletableFuture<Guild> retrieveGuildByPlayer(final String player) {
        return this.retrieve(GuildReply.class, "guild", "player", player);
    }

    /**
     * Retrieve session information.
     * Null if:
     * 1) player is offline.
     * 2) player is in lobby/housing.
     * 3) player is a staff member.
     *
     * @param uuid Dashed/undashed player UUID.
     * @return Completable future of Session.
     */
    public CompletableFuture<Session> retrieveSessionByUuid(final String uuid) {
        return this.retrieve(SessionReply.class, "session", "uuid", uuid);
    }

    /**
     * Retrieve key information.
     *
     * @return Completable future of Key.
     */
    public CompletableFuture<Key> retrieveKey() {
        return this.retrieve(KeyReply.class, "key");
    }

    /**
     * Retrieve friends of a player.
     *
     * @param uuid Dashed/undashed player UUID.
     * @return Completable future of List of Friendship.
     */
    public CompletableFuture<List<Friendship>> retrieveFriendsByUuid(final String uuid) {
        return this.retrieve(FriendsReply.class, "friends", "uuid", uuid);
    }

    private <T> CompletableFuture<T> retrieve(final Class<? extends Reply<T>> clazz, final String endpoint, final String... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Need both key and value for parameters");
        }

        final StringBuilder url = new StringBuilder()
                .append(BASE_URL)
                .append(endpoint)
                .append("?key=")
                .append(this.key);

        for (int i = 0; i < params.length - 1; i += 2) {
            final String key = params[i];
            final String value = params[i + 1];

            url.append('&').append(key).append('=').append(value);
        }

        final CompletableFuture<T> future = new CompletableFuture<>();
        final Request request = new Request.Builder()
                .url(url.toString())
                .build();

        this.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                final ResponseBody body = response.body();

                // Body is never null in Callback#onResponse.
                if (body == null) {
                    future.completeExceptionally(new IllegalStateException("Response body is null"));
                    return;
                }

                // Don't continue if we got a bad response,
                // usually it means the body is an html page
                // and we will get a json parse exception.
                if (!response.isSuccessful()) {
                    body.close();
                    future.completeExceptionally(new IllegalStateException("Response was not successful, status code: " + response.code()));
                    return;
                }

                final Reply<T> reply;

                try {
                    reply = HypixelAPI.this.objectMapper.readValue(body.string(), clazz);
                } catch (final IOException exception) {
                    future.completeExceptionally(exception);
                    return;
                }

                if (reply.isSuccess()) {
                    future.complete(reply.getValue());
                } else if (reply.isThrottle()) {
                    future.completeExceptionally(new HypixelApiThrottleException());
                } else {
                    future.completeExceptionally(new HypixelApiException(reply.getCause()));
                }
            }
        });

        return future;
    }

    /**
     * Currently set API key.
     *
     * @return Currently set API key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Change the API key.
     *
     * @param key New API key.
     */
    public void setKey(final String key) {
        this.key = key;
    }
}
