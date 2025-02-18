/*
Filter Records from CSV
Read a CSV file and filter students who have scored more than 80 marks.
Print only the qualifying records.
*/
package com.intermediateproblems.filterrecordsfromcsv;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterCSVRecords {
    public static void main(String[] args) {
        String filePath = "src/main/resources/students.csv"; // Update with the actual file path

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header row
                    continue;
                }

                String[] values = line.split(",");
                if (values.length == 4) { // Ensure correct data format
                    try {
                        int marks = Integer.parseInt(values[3]);
                        if (marks > 80) {
                            // Print qualifying student records
                            System.out.println("Student Details:");
                            System.out.println("ID    : " + values[0]);
                            System.out.println("Name  : " + values[1]);
                            System.out.println("Age   : " + values[2]);
                            System.out.println("Marks : " + values[3]);
                            System.out.println("------------------------");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid marks value: " + values[3]);
                    }
                } else {
                    System.out.println("Skipping invalid record: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
