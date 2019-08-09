package ru.mdashlw.hypixel.entities.player;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.util.DynamicObjectNode;

import java.util.Collections;
import java.util.List;

/**
 * Vanty meta.
 */
public class VanityMeta extends DynamicObjectNode {
    VanityMeta(ObjectNode node) {
        super(node);
    }

    /**
     * Packages.
     *
     * @return Packages.
     */
    public List<String> getPackages() {
        return getList("packages", Collections.emptyList(), JsonNode::asText);
    }
}
