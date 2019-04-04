package ru.mdashlw.hypixel.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

internal inline val ObjectNode.children: LinkedHashMap<String, JsonNode>
    get() = getField("_children")
