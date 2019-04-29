@file:Suppress("NOTHING_TO_INLINE")

package ru.mdashlw.hypixel.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

internal inline fun JsonNode.text() = textValue()

internal inline fun JsonNode.long() = longValue()

internal inline fun JsonNode.int() = intValue()

internal inline fun JsonNode.`object`() = this as ObjectNode
