package ru.mdashlw.hypixel.api.util

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

// TODO Move to ru.mdashlw.util:common-util, module okhttp

internal fun OkHttpClient.newCall(url: String): Response {
    val request = Request.Builder()
        .url(url)
        .build()

    return newCall(request).execute()
}
