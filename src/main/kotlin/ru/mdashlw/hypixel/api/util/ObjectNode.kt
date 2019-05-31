package ru.mdashlw.hypixel.api.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import ru.mdashlw.util.getField

// TODO Move to ru.mdashlw.util:common-util, module jackson

inline val ObjectNode.children: LinkedHashMap<String, JsonNode>
    get() = getField("_children")

inline fun <T> ObjectNode.get(field: String, default: T, adapter: (JsonNode) -> T): T =
    get(field)?.let(adapter) ?: default

inline fun <T> ObjectNode.get(field: String, adapter: (JsonNode) -> T): T =
    get(field).let(adapter)
