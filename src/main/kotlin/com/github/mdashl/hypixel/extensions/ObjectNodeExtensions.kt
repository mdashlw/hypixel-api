package com.github.mdashl.hypixel.extensions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.mdashl.hypixel.utils.getField

internal val ObjectNode.children: LinkedHashMap<String, JsonNode>
    get() = getField("_children")
