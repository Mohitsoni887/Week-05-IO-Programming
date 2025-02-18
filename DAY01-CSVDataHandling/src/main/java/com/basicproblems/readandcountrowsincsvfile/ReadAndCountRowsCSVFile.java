/*
Read and Count Rows in a CSV File
Read a CSV file and count the number of records (excluding the header row).
*/
package com.basicproblems.readandcountrowsincsvfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndCountRowsCSVFile {
    public static void main(String[] args) {
        String filePath = "src/main/resources/employees.csv"; // Update with the actual file path
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            // Read through the file and count rows
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header row
                    continue;
                }

                // Each non-header line is a record
                rowCount++;
            }

            System.out.println("Total number of records (excluding header): " + rowCount);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

