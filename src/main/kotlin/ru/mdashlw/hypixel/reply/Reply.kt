package ru.mdashlw.hypixel.reply

abstract class Reply<T>(
    val success: Boolean,
    val cause: String?,
    val throttle: Boolean,
    val element: T?
)