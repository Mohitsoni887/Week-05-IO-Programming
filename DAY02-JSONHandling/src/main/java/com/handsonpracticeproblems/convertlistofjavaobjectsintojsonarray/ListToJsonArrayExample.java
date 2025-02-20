/*
Convert a list of Java objects into a JSON array.
 */
package com.handsonpracticeproblems.convertlistofjavaobjectsintojsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class Student {
    private String name;
    private int age;
    private List<String> subjects;

    // Constructor
    public Student(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    // Getters (required for Jackson)
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getSubjects() { return subjects; }
}

public class ListToJsonArrayExample {
    public static void main(String[] args) {
        try {
            // Create a list of Student objects
            List<Student> students = Arrays.asList(
                    new Student("Alice", 22, Arrays.asList("Math", "Physics")),
                    new Student("Bob", 25, Arrays.asList("Computer Science", "Algorithms")),
                    new Student("Charlie", 23, Arrays.asList("Java", "Databases"))
            );

            // Convert list to JSON array
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);

            // Print JSON array
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
