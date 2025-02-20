/*
 Filter JSON data: Print only users older than 25 years.
 */
package com.handsonpracticeproblems.filterjsondata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.stream.Collectors;

class User {
    private String name;
    private String email;
    private int age;

    // Constructor (needed for object creation)
    public User() {}

    // Getters (required for Jackson)
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}

public class FilterJsonData {
    public static void main(String[] args) {
        String jsonArray = """
        [
            {"name": "Alice", "email": "alice@example.com", "age": 25},
            {"name": "Bob", "email": "bob@example.com", "age": 30},
            {"name": "Charlie", "email": "charlie@example.com", "age": 28}
        ]
        """;

        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON array into List of User objects
            List<User> users = objectMapper.readValue(jsonArray, new TypeReference<List<User>>() {});

            // Filter users where age > 25
            List<User> filteredUsers = users.stream()
                    .filter(user -> user.getAge() > 25)
                    .collect(Collectors.toList());

            // Convert filtered list back to JSON
            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredUsers);

            // Print the filtered JSON
            System.out.println(filteredJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
