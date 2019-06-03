@file:Suppress("NOTHING_TO_INLINE")

package ru.mdashlw.hypixel.api.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

// TODO Move to ru.mdashlw.util:common-util, module jackson

inline fun JsonNode.text(): String = textValue()

inline fun JsonNode.long() = longValue()

inline fun JsonNode.int() = intValue()

inline fun JsonNode.obj() = this as ObjectNode
