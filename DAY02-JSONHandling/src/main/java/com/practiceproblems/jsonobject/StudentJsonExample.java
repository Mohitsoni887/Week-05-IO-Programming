/*
Create a JSON object for a Student with fields: name, age, and subjects (array).
 */
package com.practiceproblems.jsonobject;

import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;

public class StudentJsonExample {
    public static void main(String[] args) {
        // Create a JSON object for Student
        JSONObject studentJson = new JSONObject();
        studentJson.put("name", "John Doe");
        studentJson.put("age", 20);

        // Adding an array of subjects
        List<String> subjects = Arrays.asList("Mathematics", "Computer Science", "Physics");
        studentJson.put("subjects", subjects);

        // Print the JSON representation
        System.out.println(studentJson.toString(4));
    }
}
