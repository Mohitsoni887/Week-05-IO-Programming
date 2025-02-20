/*
Parse JSON and filter only those records where age > 25.
 */
package com.practiceproblems.parsejsonandfilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    private String name;
    private String email;
    private int age;

    // Constructor (needed for object creation)
    public Person() {}

    // Getters (required for Jackson)
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}

public class ParseJsonAndFilter {
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

            // Parse JSON array into List of Person objects
            List<Person> people = objectMapper.readValue(jsonArray, new TypeReference<List<Person>>() {});

            // Filter records where age > 25
            List<Person> filteredPeople = people.stream()
                    .filter(person -> person.getAge() > 25)
                    .collect(Collectors.toList());

            // Convert filtered list back to JSON
            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredPeople);

            // Print the filtered JSON
            System.out.println(filteredJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
