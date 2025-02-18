/*
Merge Two CSV Files
You have two CSV files:
students1.csv (contains ID, Name, Age)
students2.csv (contains ID, Marks, Grade)
Merge both files based on ID and create a new file containing all details.
*/
package com.advancedproblems.mergetwocsvfiles;

import java.io.*;
import java.util.*;

class Student {
    int id;
    String name;
    int age;
    double marks;
    String grade;

    public Student(int id, String name, int age, double marks, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + marks + "," + grade;
    }
}

public class MergeCSVFiles {
    public static void main(String[] args) {
        String file1 = "src/main/resources/students1.csv"; // Contains ID, Name, Age
        String file2 = "src/main/resources/students2.csv"; // Contains ID, Marks, Grade
        String outputFile = "src/main/resources/merged_students.csv";

        Map<Integer, Student> studentMap = new HashMap<>();

        // Read the first CSV file (ID, Name, Age)
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                int id = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                int age = Integer.parseInt(values[2].trim());

                studentMap.put(id, new Student(id, name, age, 0, ""));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file1);
        }

        // Read the second CSV file (ID, Marks, Grade)
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                int id = Integer.parseInt(values[0].trim());
                double marks = Double.parseDouble(values[1].trim());
                String grade = values[2].trim();

                if (studentMap.containsKey(id)) {
                    Student student = studentMap.get(id);
                    student.marks = marks;
                    student.grade = grade;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file2);
        }

        // Write the merged data to a new CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();

            for (Student student : studentMap.values()) {
                bw.write(student.toString());
                bw.newLine();
            }

            System.out.println("Merged CSV file created successfully: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFile);
        }
    }
}
