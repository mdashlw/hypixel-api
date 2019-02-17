package com.github.mdashl.hypixel.reply

abstract class Reply<T>(val throttle: Boolean, val success: Boolean, val cause: String? = null) {

    abstract val element: T?

}
