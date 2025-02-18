/*
Sort CSV Records by a Column
Read a CSV file and sort the records by Salary in descending order.
Print the top 5 highest-paid employees.
*/
package com.intermediateproblems.sortcsvrecordscolumn;

import java.io.*;
import java.util.*;

public class SortCSVBySalary {
    public static void main(String[] args) {
        String filePath = "src/main/resources/employees.csv"; // Input CSV file
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            String[] header = null;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (isHeader) {
                    header = values; // Store header separately
                    isHeader = false;
                    continue;
                }

                if (values.length == 4) { // Ensure correct data format
                    records.add(values);
                }
            }

            // Sort records by Salary in descending order
            records.sort((a, b) -> {
                double salaryA = Double.parseDouble(a[3].trim());
                double salaryB = Double.parseDouble(b[3].trim());
                return Double.compare(salaryB, salaryA); // Descending order
            });

            // Print header
            System.out.println(String.join(", ", header));

            // Print top 5 highest-paid employees
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                System.out.println(String.join(", ", records.get(i)));
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
