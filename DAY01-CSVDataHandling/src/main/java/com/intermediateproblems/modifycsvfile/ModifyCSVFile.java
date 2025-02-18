/*
Modify a CSV File (Update a Value)
Read a CSV file and increase the salary of employees from the "IT" department by 10%.
Save the updated records back to a new CSV file.
 */
package com.intermediateproblems.modifycsvfile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyCSVFile {
    public static void main(String[] args) {
        String inputFile = "src/main/resources/employees.csv";         // Input CSV file
        String outputFile = "src/main/resources/updated_employees.csv"; // Output CSV file

        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Store header separately
                if (isHeader) {
                    isHeader = false;
                    records.add(values); // Add header as-is
                    continue;
                }

                // Check if the record has 4 columns
                if (values.length == 4) {
                    String department = values[2].trim();
                    try {
                        double salary = Double.parseDouble(values[3].trim());

                        // Increase salary by 10% for IT department employees
                        if (department.equalsIgnoreCase("IT")) {
                            salary *= 1.10; // Increase by 10%
                            values[3] = String.format("%.2f", salary); // Format salary
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid salary value: " + values[3]);
                    }
                }

                // Store the updated record
                records.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Write updated records to the new CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String[] record : records) {
                bw.write(String.join(",", record)); // Write each row
                bw.newLine();
            }
            System.out.println("Updated file saved as: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
