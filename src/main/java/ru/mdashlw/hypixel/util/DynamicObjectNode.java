package ru.mdashlw.hypixel.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;
import java.util.function.Function;

public class DynamicObjectNode extends ObjectNode {
    public DynamicObjectNode(ObjectNode node) {
        super(JsonNodeFactory.instance);
        setAll(node);
    }

    protected <T> T get(String field, T fallback, Function<JsonNode, T> adapter) {
        JsonNode node = get(field);

        if (node == null || node.isNull()) {
            return fallback;
        }

        return adapter.apply(node);
    }

    protected <T> List<T> getList(String field, List<T> fallback, Function<JsonNode, T> adapter) {
        JsonNode arrayNode = get(field);

        if (arrayNode == null || !arrayNode.isArray()) {
            return fallback;
        }

        Iterator<JsonNode> elements = arrayNode.elements();
        ArrayList<T> objects = new ArrayList<>();

        while (elements.hasNext()) {
            JsonNode node = elements.next();

            objects.add(adapter.apply(node));
        }

        return objects;
    }

    protected <K, V> Map<K, V> getMap(String field, Map<K, V> fallback, Function<String, K> keyAdapter, Function<JsonNode, V> valueAdapter) {
        JsonNode objectNode = get(field);

        if (objectNode == null || !objectNode.isObject()) {
            return fallback;
        }

        Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
        HashMap<K, V> objects = new HashMap<>();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            JsonNode value = entry.getValue();

            objects.put(keyAdapter.apply(key), valueAdapter.apply(value));
        }

        return objects;
    }
}
