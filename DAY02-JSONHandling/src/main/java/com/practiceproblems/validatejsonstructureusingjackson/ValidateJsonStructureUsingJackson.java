/*
Validate JSON structure using Jackson.
*/
package com.practiceproblems.validatejsonstructureusingjackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ValidateJsonStructureUsingJackson {
    public static void main(String[] args) {
        String jsonString = "{ \"name\": \"Alice\", \"email\": \"alice@example.com\", \"age\": 25 }";

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Validate JSON structure by parsing into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // If valid, print confirmation
            System.out.println("Valid JSON: " + jsonNode.toPrettyString());
        } catch (JsonProcessingException e) {
            // Handle invalid JSON
            System.out.println("Invalid JSON: " + e.getMessage());
        }
    }
}
