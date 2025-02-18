/*
 Read a CSV File and Print Data
Read a CSV file containing student details (ID, Name, Age, Marks).
Print each record in a structured format.
*/
package com.basicproblems.readcsvfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFile {
    public static void main(String[] args) {
        String filePath = "src/main/resources/students.csv";

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
                    System.out.println("Student Details:");
                    System.out.println("ID    : " + values[0]);
                    System.out.println("Name  : " + values[1]);
                    System.out.println("Age   : " + values[2]);
                    System.out.println("Marks : " + values[3]);
                    System.out.println("------------------------");
                } else {
                    System.out.println("Skipping invalid record: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
