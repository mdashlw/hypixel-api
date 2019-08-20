package ru.mdashlw.hypixel.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;
import java.util.function.Function;

public class DynamicObjectNode extends ObjectNode {
    public DynamicObjectNode(final ObjectNode node) {
        super(JsonNodeFactory.instance);
        this.setAll(node);
    }

    public final <T> T get(final String field, final T fallback, final Function<? super JsonNode, ? extends T> adapter) {
        final JsonNode node = this.get(field);

        if (node == null || node.isNull()) {
            return fallback;
        }

        return adapter.apply(node);
    }

    public final <T> List<T> getList(final String field, final List<T> fallback, final Function<? super JsonNode, ? extends T> adapter) {
        final JsonNode arrayNode = this.get(field);

        if (arrayNode == null || !arrayNode.isArray()) {
            return fallback;
        }

        final Iterator<JsonNode> elements = arrayNode.elements();
        final ArrayList<T> objects = new ArrayList<>();

        while (elements.hasNext()) {
            final JsonNode node = elements.next();

            objects.add(adapter.apply(node));
        }

        return objects;
    }

    public final <K, V> Map<K, V> getMap(final String field, final Map<K, V> fallback, final Function<? super String, ? extends K> keyAdapter, final Function<? super JsonNode, ? extends V> valueAdapter) {
        final JsonNode objectNode = this.get(field);

        if (objectNode == null || !objectNode.isObject()) {
            return fallback;
        }

        final Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
        final HashMap<K, V> objects = new HashMap<>();

        while (fields.hasNext()) {
            final Map.Entry<String, JsonNode> entry = fields.next();
            final String key = entry.getKey();
            final JsonNode value = entry.getValue();

            objects.put(keyAdapter.apply(key), valueAdapter.apply(value));
        }

        return objects;
    }
}
