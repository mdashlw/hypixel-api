package ru.mdashlw.hypixel.entities.player;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.mdashlw.hypixel.entities.player.stats.SkyWars;
import ru.mdashlw.hypixel.entities.player.stats.UHC;
import ru.mdashlw.hypixel.util.DynamicObjectNode;

/**
 * Player stats.
 */
public final class Stats extends DynamicObjectNode {
    Stats(final ObjectNode node) {
        super(node);
    }

    /**
     * Stats on SkyWars.
     *
     * @return SkyWars stats or null.
     */
    public SkyWars getSkyWars() {
        return this.get("SkyWars", null, node -> new SkyWars((ObjectNode) node));
    }

    /**
     * Stats on UHC Champions.
     *
     * @return UHC stats or null.
     */
    public UHC getUHC() {
        return this.get("UHC", null, node -> new UHC((ObjectNode) node));
    }
}
