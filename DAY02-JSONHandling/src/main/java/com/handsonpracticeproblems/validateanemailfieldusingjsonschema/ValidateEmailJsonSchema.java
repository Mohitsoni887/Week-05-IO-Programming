/*
Validate an email field using JSON Schema.
 */
package com.handsonpracticeproblems.validateanemailfieldusingjsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class ValidateEmailJsonSchema {
    public static void main(String[] args) {
        String jsonString = """
        {
            "name": "Alice",
            "email": "alice@example.com"
        }
        """;

        String jsonSchema = """
        {
            "$schema": "http://json-schema.org/draft-07/schema#",
            "type": "object",
            "properties": {
                "name": { "type": "string" },
                "email": {
                    "type": "string",
                    "format": "email"
                }
            },
            "required": ["email"]
        }
        """;

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON and Schema
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode schemaNode = objectMapper.readTree(jsonSchema);

            // Create Schema Validator
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            // Validate JSON
            ProcessingReport report = schema.validate(jsonNode);

            // Print Validation Result
            if (report.isSuccess()) {
                System.out.println("JSON is valid!");
            } else {
                System.out.println("JSON is invalid:");
                report.forEach(msg -> System.out.println(msg));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

