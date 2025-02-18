/*
Search for a Record in CSV
Read an employees.csv file and search for an employee by name.
Print their department and salary.
*/
package com.intermediateproblems.searchrecordincsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchCSVRecord {
    public static void main(String[] args) {
        String filePath = "src/main/resources/employees.csv"; // Update with the actual file path
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim(); // Get the employee name to search

        boolean recordFound = false;

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
                    String employeeName = values[1].trim(); // Name is the second field

                    // Check if the employee name matches the search query
                    if (employeeName.equalsIgnoreCase(searchName)) {
                        recordFound = true;
                        System.out.println("Employee Found:");
                        System.out.println("Name      : " + values[1]);
                        System.out.println("Department: " + values[2]);
                        System.out.println("Salary    : " + values[3]);
                        break; // Stop after finding the record
                    }
                }
            }

            if (!recordFound) {
                System.out.println("Employee with name '" + searchName + "' not found.");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
