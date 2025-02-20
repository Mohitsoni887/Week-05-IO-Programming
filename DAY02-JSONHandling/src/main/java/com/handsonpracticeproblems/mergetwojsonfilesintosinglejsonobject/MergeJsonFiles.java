/*
 Merge two JSON files into a single JSON object.
 */
package com.handsonpracticeproblems.mergetwojsonfilesintosinglejsonobject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class MergeJsonFiles {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read first JSON file
            JsonNode json1 = objectMapper.readTree(new File("src/main/resources/file1.json"));

            // Read second JSON file
            JsonNode json2 = objectMapper.readTree(new File("src/main/resources/file2.json"));

            // Merge JSON files
            JsonNode mergedJson = mergeJson(json1, json2);

            // Convert merged JSON to a formatted string
            String mergedJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson);

            // Print merged JSON
            System.out.println("Merged JSON:\n" + mergedJsonString);

            // Optionally, write merged JSON to a new file
            objectMapper.writeValue(new File("merged.json"), mergedJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonNode mergeJson(JsonNode mainNode, JsonNode updateNode) {
        if (mainNode instanceof ObjectNode && updateNode instanceof ObjectNode) {
            ObjectNode mainObject = (ObjectNode) mainNode;
            updateNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();

                // Recursively merge nested objects
                if (mainObject.has(key) && mainObject.get(key).isObject() && value.isObject()) {
                    mergeJson(mainObject.get(key), value);
                } else {
                    mainObject.set(key, value);
                }
            });
            return mainObject;
        }
        return updateNode;
    }
}
