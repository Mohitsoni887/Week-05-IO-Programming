/*
Read a JSON file and print all keys and values.
 */
package com.handsonpracticeproblems.readjsonfileandprintallkeysandvalues;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class ReadJsonKeysValues {
    public static void main(String[] args) {
        try {
            // Read the JSON file into a String
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data1.json")));

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON content into a JsonNode
            JsonNode rootNode = objectMapper.readTree(content);

            // Print all keys and values
            printJsonKeysAndValues(rootNode, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recursive method to traverse JSON and print keys and values
    private static void printJsonKeysAndValues(JsonNode node, String parentKey) {
        if (node.isObject()) {
            // Iterate through the keys in the JSON object
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                printJsonKeysAndValues(field.getValue(), parentKey + field.getKey() + ".");
            }
        } else if (node.isArray()) {
            // Iterate through elements in the JSON array
            for (int i = 0; i < node.size(); i++) {
                printJsonKeysAndValues(node.get(i), parentKey + "[" + i + "].");
            }
        } else {
            // Print the key and value
            System.out.println(parentKey.substring(0, parentKey.length() - 1) + " : " + node.asText());
        }
    }
}
