package ru.mdashlw.hypixel.api.entities

data class Key(
    val ownerUuid: String,
    val key: String,
    val totalQueries: Int,
    val queriesInPastMin: Int?
)
