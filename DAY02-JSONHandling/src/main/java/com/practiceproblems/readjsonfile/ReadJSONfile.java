/*
Read a JSON file and extract only specific fields (e.g., name, email).
 */
package com.practiceproblems.readjsonfile;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJSONfile {
    public static void main(String[] args) {
        try {
            // Read the JSON file content
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data.json")));

            // Parse JSON content into JSONObject
            JSONObject jsonObject = new JSONObject(content);

            // Extract specific fields
            String name = jsonObject.optString("name", "N/A"); // Default "N/A" if not found
            String email = jsonObject.optString("email", "N/A");

            // Print extracted data
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
