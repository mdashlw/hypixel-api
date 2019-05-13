@file:Suppress("NOTHING_TO_INLINE")

package ru.mdashlw.hypixel.api.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

// TODO Move to ru.mdashlw.util:common-util, module jackson

internal inline fun JsonNode.text() = textValue()

internal inline fun JsonNode.long() = longValue()

internal inline fun JsonNode.int() = intValue()

internal inline fun JsonNode.`object`() = this as ObjectNode
