package com.github.mdashl.hypixel.extensions

import okhttp3.OkHttpClient
import okhttp3.Request

fun OkHttpClient.call(url: String): String =
    newCall(Request.Builder().url(url).build()).execute().body()!!.string()
