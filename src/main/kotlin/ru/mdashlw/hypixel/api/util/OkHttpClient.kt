package ru.mdashlw.hypixel.api.util

import okhttp3.OkHttpClient
import okhttp3.Request

// TODO Move to ru.mdashlw.util:common-util, module okhttp

internal fun OkHttpClient.newCall(url: String): String =
    newCall(Request.Builder().url(url).build()).execute().body()!!.string()
