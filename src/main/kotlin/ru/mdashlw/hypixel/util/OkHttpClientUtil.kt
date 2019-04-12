package ru.mdashlw.hypixel.util

import okhttp3.OkHttpClient
import okhttp3.Request

internal fun OkHttpClient.newCall(url: String): String =
    newCall(Request.Builder().url(url).build()).execute().body()!!.string()
