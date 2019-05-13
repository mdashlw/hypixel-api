package ru.mdashlw.hypixel.api.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

// TODO Move to ru.mdashlw.util:common-util, module jackson

internal inline val ObjectNode.children: LinkedHashMap<String, JsonNode>
    get() = getField("_children")

internal inline fun <T> ObjectNode.get(field: String, default: T, adapter: (JsonNode) -> T): T =
    get(field)?.let(adapter) ?: default

internal inline fun <T> ObjectNode.get(field: String, adapter: (JsonNode) -> T): T =
    get(field).let(adapter)
