/*
Convert CSV Data into Java Objects
Read a CSV file and convert each row into a Student Java object.
Store the objects in a List<Student> and print them.
*/

package com.advancedproblems.convertcsvdataintojavaobjects;
import java.io.*;
import java.util.*;

public class ConvertCSVDataintoJavaObjects {
    public static void main(String[] args) {
        String filePath = "src/main/resources/students.csv"; // Input CSV file
        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Ensure correct number of columns
                if (values.length != 4) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    int age = Integer.parseInt(values[2].trim());
                    double marks = Double.parseDouble(values[3].trim());

                    // Create Student object and add to list
                    studentList.add(new Student(id, name, age, marks));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid row (parsing error): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Print the Student objects
        System.out.println("Students List:");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
